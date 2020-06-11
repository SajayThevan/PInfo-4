import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule } from "@angular/forms";
import { Observable, from } from 'rxjs';
import { ProfileService } from '../services/profile/profile.service'
import { RecipeService } from '../services/recipe/recipe.service';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;
  public quantityForm:FormGroup;
  public quantity:FormControl;

  constructor(private formBuilder:FormBuilder,private profileService: ProfileService, private recipeService: RecipeService, private ingredientService: IngredientService, public keycloak: KeycloakService) {
    this.quantity=new FormControl('',[Validators.required])
    this.quantityForm=formBuilder.group({
    quantity:this.quantity
    })
   }



  fridgeInter = []
  Ingredients : Object
  Ingredient_Name = [];

  public profile$: Observable<any>;
  public recipes$: Observable<any>;
  public favourites$: Observable<any>;
  public fridge$: Observable<any>;
  public fridge: any; // TODO: Not the cleanest way, would be nicer to directly affect the reponse of the observable rather than assign it to a new variable
                      // Done like this as quantities are added to fridge and not sure how to add elements to the observable response


   

  ngOnInit(): void {


    this.ingredientService.getAllIngredientsResearch().subscribe(
      (data : Response) => {
        this.Ingredients = data;
       });

    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      this.profileService.profileExists(this.keycloak.getID()).subscribe(
        (profileExists : any) => {
          if (!profileExists) {
            this.createProfile().subscribe(
              (reponse: any) => {
                this.getProfileDetails()
            });
          } else {
            this.getProfileDetails();
          }
      });
    }
    //this.fridgeInter = this.fridge
  }

  createProfile() {
    let profile: any = {};
    profile.id = this.keycloak.getID();
    profile.pseudo = this.keycloak.getUsername();
    profile.email = this.keycloak.getEmail();
    profile.firstName = this.keycloak.getFirstName();
    profile.lastName = this.keycloak.getLastName();
    profile.score = 0;
    profile.fridgeContents = [];
    profile.favouriteRecipes = [];
    return this.profileService.createProfile(profile);
  }

  getProfileDetails() {
    // Get Profile
    this.profile$ = this.profileService.getProfile(this.keycloak.getID())
    this.profileService.addIngredientById(this.keycloak.getID(),1,2)
    this.profile$ = this.profileService.getProfile(this.keycloak.getID())
    this.profile$.subscribe(
        (profile : any) => {          
          // Get Fridge Contents
          var ingIDs = [];
          profile.fridgeContents.forEach(element => {
            ingIDs.push(element.ingredientId);
          });
          // For each id : id --> Ingredient Name
          //this.Ingredient_Name = this.ingredientService.getIngredients(ingIDs)
          this.fridge$ = this.ingredientService.getIngredients(ingIDs);
          this.fridge$.subscribe(
            (response : any) => {
              this.fridge = response;
              for (let i = 0; i < this.fridge.length; i++) {
                this.fridge[i].quantity = profile.fridgeContents[i].quantity;
              };
          });
          //Get Favourite Recipes
          var recipeIDs = [];
          profile.favouriteRecipes.forEach(element => {
            recipeIDs.push(element.recipeId);
          });
          this.favourites$ = this.recipeService.getRecipes(recipeIDs);
    });
    // Get my recipes
    this.recipes$ = this.recipeService.getRecipeforProfile(this.keycloak.getID());
    this.fridgeInter = this.fridge;
  }

  logout() {
    this.keycloak.logout();
  }

  selected = [];

  Add(){
    console.log("Added")
    console.log(this.quantityForm.get('quantity').value)
    this.fridgeInter.push({
      id : this.selected[0].id,
      quantity : +this.quantityForm.get('quantity').value
    })
    this.Ingredient_Name.push(this.selected[0].name)
    console.log(this.fridge)
  }

  Remove(id){
    console.log("Removed")
    this.fridgeInter.splice(id,1)
    this.Ingredient_Name.splice(id,1)
    // removeIngredient(profileID,ingredientID)
    console.log(this.fridge)
  }

  saveFridge(){
    // this.Frigo --> BACKEND
    this.fridge = this.fridgeInter;
  }

  Notsave(){
    this.getProfileDetails()
  }

}
