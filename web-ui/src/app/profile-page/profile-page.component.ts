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
  public id;

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
              console.log("getProfildetails")
              console.log(this.fridge)

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
    console.log("ici",this.fridgeInter)
    
  
  }

  Remove(id){
    this.fridgeInter.splice(id,1)
   
  }

  async saveFridge(){
    console.log(this.fridge)
    console.log("jdkdhfjkdf",this.fridgeInter)
    var remove = new Promise((resolve, reject) => {
      if (this.fridge.length == 0){resolve();}

      this.fridge.forEach((element1, index, array) => {
        console.log("index remove: ",index)
        let ret = this.profileService.removeIngredient(this.id,element1.id)
        if (index === this.fridge.length -1){ resolve();}
      });
    });
    
    remove.then(() => {
      var add = new Promise((resolve, reject) => {
        console.log("Avant Add; ",this.fridgeInter)
        console.log(this.fridgeInter.length-1)
        this.fridgeInter.forEach((element, index2, array2)=>{
          console.log("Add:",element)
          console.log("index add: ",index2)
          let ret = this.profileService.addIngredientById(this.id,element.id,element.quantity)
          if (index2 === this.fridgeInter.length -1) {resolve();}
        })
        
      });
      add.then(async () => {
        //this.fridge=[]  
        //this.fridgeInter=[]
        console.log("ici 1 avant")
        await this.delay(750)
        console.log("ici 1 apres")
        console.log("La")
        console.log("avant vidage")
        console.log(this.fridge)
        console.log(this.fridgeInter)

        var clear = new Promise((resolve, reject) => {
          this.fridge = []
          this.fridgeInter = [];
          resolve();
        });
        clear.then(async () => {
          console.log("après vidage")
          console.log(this.fridge)
          console.log(this.fridgeInter)
          //this.getProfileDetails()
          var update = new Promise(async (resolve, reject) => {
            let ret = await this.getProfileDetails();
            console.log("ici 2 avant")
            await this.delay(750)
            console.log("ici 2 apres")
            resolve();
          });
          update.then(async () => {
            
            console.log("Hope")
          });
        });
        
      });
    });
    
  }
 

  async Notsave(){
    await this.getProfileDetails()
  }


  addRecipe() {
    let Recipe: any = {};
    Recipe.authorID = this.keycloak.getID();
    Recipe.name = (document.getElementById("name") as HTMLInputElement).value;
    Recipe.date = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
    Recipe.imagePath ="tmp/image/recipe/"+stringify(Recipe.authorID)+".jpg"
    Recipe.ingredients = []
    Recipe.steps = [];
    Recipe.category = [];
    Recipe.difficulty =(document.getElementById("difficulty") as HTMLInputElement).value;
    Recipe.time = (document.getElementById("time") as HTMLInputElement).value;
    Recipe.ratings = [];
    Recipe.comments = [];
    console.log(Recipe)
    //let ret = this.recipeService.createNewRecipe(Recipe)
    window.location.reload();
   
  }

  async removeFav(recipeid){
    console.log(recipeid)
    let ret = this.profileService.removeFavourite(this.keycloak.getID(),recipeid)
    await this.delay(500)
    this.getProfileDetails();
  }


  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }

}
