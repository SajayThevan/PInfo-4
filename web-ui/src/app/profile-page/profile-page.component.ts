import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule, SelectMultipleControlValueAccessor } from "@angular/forms";
import { Observable, from } from 'rxjs';
import { ProfileService } from '../services/profile/profile.service'
import { RecipeService } from '../services/recipe/recipe.service';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { threadId } from 'worker_threads';
import { element, promise } from 'protractor';
import { stringify } from 'querystring';
import { DatePipe } from '@angular/common';
import { async } from '@angular/core/testing';

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
  public nameRecipe: FormControl;

  public fridgeInter = [];
  public Ingredients : Object;
 
  public selected = [];

  public ret = 0;
  public profile$: Observable<any>;
  public recipes$: Observable<any>;
  public favourites$: Observable<any>;
  public fridge$: Observable<any>;
  public fridge: any; 
  public id;
 

  //create recipe var
  public selectedIngredients = [];
  public ingredients_Recipe = [];
  public ingredient_backend = [];
  public steps = [];


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
    nameRecipe:this.nameRecipe,
    cat:this.cat,
    difficulty:this.difficulty,
    time:this.time,
    instruction:this.instruction
    })
   }

  ngOnInit(): void {

    this.id = this.keycloak.getID();
    this.ingredientService.getAllIngredientsResearch().subscribe(
      (data : Response) => {
        this.Ingredients = data;
       });

    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      this.profileService.profileExists(this.keycloak.getID()).subscribe(
        async (profileExists : any) => {
          if (!profileExists) {
            this.createProfile().subscribe(
              (reponse: any) => {
                this.getProfileDetails()
            });
          } else {
            await this.getProfileDetails();
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

  async getProfileDetails(): Promise<any> {
    // Get Profile
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
          this.favourites$.subscribe((response : any) => {
            console.log(recipeIDs)
            console.log(response)
          });
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
      quantity : this.quantityForm.get('quantity').value
    })    
  }

  Remove(id){
    this.fridgeInter.splice(id,1)
  }

  async saveFridge(){
    for (let element of this.fridge) {
      await this.profileService.removeIngredient(this.id,element.id).toPromise()
    }
    for (let element of this.fridgeInter) {
      await this.profileService.addIngredientById(this.id,element.id,element.quantity).toPromise()
    }
    this.fridge = []
    this.fridgeInter = [];
    this.getProfileDetails();
  }
 
  async Notsave(){
    await this.getProfileDetails()
  }

  addIngredient(){
     if(this.selectedIngredients.length > 0) {
        this.ingredients_Recipe.push(this.selectedIngredients[0])
        let quantity = +(document.getElementById("quantity") as HTMLInputElement).value;
        this.ingredient_backend.push({quantite:quantity ,ingredientId:this.selectedIngredients[0].id})
        this.selectedIngredients = [];
     } 
  }

  addSteps(){
    this.steps.push({step:(document.getElementById("instruction") as HTMLInputElement).value})
  }

  Categories = [{categories:"DINNER"},{categories:"DESERT"},{categories:"LUNCH"},{categories:"BREAKFAST"},{categories:"VEGETARIAN"}
  ,{categories:"VEGAN"}]
  categories_Selected = [];


  async addRecipe() {
    let Recipe: any = {};
    Recipe.name = (document.getElementById("nameRecipe") as HTMLInputElement).value;
    Recipe.authorID = this.id;
    Recipe.date = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
    Recipe.imagePath = "/tmp/images/logo.png";
    Recipe.ingredients = this.ingredient_backend;
    Recipe.steps = this.steps;
    Recipe.category = this.categories_Selected;
    Recipe.difficulty = +(document.getElementById("difficulty") as HTMLInputElement).value;
    Recipe.time = +(document.getElementById("time") as HTMLInputElement).value;
    Recipe.ratings = [{"rate":0}];
    Recipe.comments = [];
    var add = new Promise((resolve, reject) => {
      let ret = this.recipeService.createNewRecipe(Recipe).subscribe((data: Response)=>{
        resolve();
      });
    });
    add.then(async () => {
      this.getProfileDetails();
    });
  }

  async removeRecipe(recipeid){
    await this.recipeService.deleteRecipe(recipeid).toPromise()
    this.getProfileDetails();
  }

  async removeFav(recipeid){
    await this.profileService.removeFavourite(this.keycloak.getID(),recipeid).toPromise()
    this.getProfileDetails();
  }

}
