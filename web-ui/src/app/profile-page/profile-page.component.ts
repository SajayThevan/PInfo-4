import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule } from "@angular/forms";
import { Observable, from } from 'rxjs';
import { ProfileService } from '../services/profile/profile.service'
import { RecipeService } from '../services/recipe/recipe.service';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { threadId } from 'worker_threads';
import { element } from 'protractor';
import { stringify } from 'querystring';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss'],
  providers: [DatePipe]
})
export class ProfilePageComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;
  public quantityForm:FormGroup;
  public recipeForm: FormGroup;
  public quantity:FormControl;

  public name:FormControl;
  public cat: FormControl;
  public difficulty: FormControl;
  public time: FormControl;
  public instruction: FormControl;

  public fridgeInter = [];
  public Ingredients : Object;
 
  selected = [];

  public ret = 0;
  public profile$: Observable<any>;
  public recipes$: Observable<any>;
  public favourites$: Observable<any>;
  public fridge$: Observable<any>;
  public fridge: any; 
   

  constructor(private formBuilder:FormBuilder,private profileService: ProfileService, private recipeService: RecipeService, private ingredientService: IngredientService, public keycloak: KeycloakService,private datePipe: DatePipe) {
    this.quantity=new FormControl('',[Validators.required])

    this.quantityForm=formBuilder.group({
    quantity:this.quantity
    })
  

    //TODO: Verify quantity
    this.name = new FormControl('',[Validators.required]);
    this.cat = new FormControl('',[Validators.required]);
    this.difficulty =  new FormControl('',[Validators.required]);
    this.time = new FormControl('',[Validators.required]);
    this.instruction = new FormControl('',[Validators.required]);

    this.recipeForm=formBuilder.group({
    name:this.name,
    cat:this.cat,
    difficulty:this.difficulty,
    time:this.time,
    instruction:this.instruction
    })
   }

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
            this.ret = this.getProfileDetails();
          }
      });
    }
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
    this.profile$ = this.profileService.getProfile(this.keycloak.getID());
    this.profileService.addIngredientById(this.keycloak.getID(),1,2);
    this.profile$ = this.profileService.getProfile(this.keycloak.getID());
    this.profile$.subscribe(
        (profile : any) => {          
          // Get Fridge Contents
          var ingIDs = [];
          profile.fridgeContents.forEach(element => {
            ingIDs.push(element.ingredientId);
          });
        
          this.fridge$ = this.ingredientService.getIngredients(ingIDs);
          this.fridge$.subscribe(
            (response : any) => {
              this.fridge = response;
              this.fridge.forEach(ing =>{
                profile.fridgeContents.forEach(element =>{
                  if (ing.id == element.ingredientId){
                    ing.quantity = element.quantity
                  }
                });
              })

              for (let i = 0; i < this.fridge.length; i++) {
                this.fridgeInter[i] = this.fridge[i];
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

    return 1;
  }

  logout() {
    this.keycloak.logout();
  }

  

  Add(){
    this.fridgeInter.push({
      id : this.selected[0].id,
      name: this.selected[0].name,
      quantity : +this.quantityForm.get('quantity').value
    })
    
  
  }

  Remove(id){
    this.fridgeInter.splice(id,1)
   
  }

  saveFridge(){

     this.fridge.forEach(element => {
      let ret = this.profileService.removeIngredient(this.keycloak.getID(),element.id)
    });
    this.fridgeInter.forEach(element=>{
      let ret = this.profileService.addIngredientById(this.keycloak.getID(),element.id,element.quantity)
    })

    this.ngOnInit()

  }

  Notsave(){
    this.getProfileDetails()
  }


  createRecipe() {
    let Recipe: any = {};
    Recipe.authorID = this.keycloak.getID();
    Recipe.name = this.recipeForm.get('name').value
    Recipe.date = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
    Recipe.imagePath ="tmp/image/recipe/"+stringify(Recipe.authorID)+".jpg"
    Recipe.ingredients = []
    Recipe.steps = [];
    Recipe.category = [];
    Recipe.difficulty =this.recipeForm.get('diffuclty').value
    Recipe.time = this.recipeForm.get('time').value
    Recipe.ratings = [];
    Recipe.comments = [];
    console.log(Recipe)

    
   // return this.profileService.createProfile(profile);
  }


}
