import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router'
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { RecipeService } from '../services/recipe/recipe.service';
import { ChallengeService } from '../services/challenge/challenge.service';
import { KeycloakInstance } from 'keycloak-js';
import { stringify } from 'querystring';
import { Observable, from, ObservedValueOf } from 'rxjs';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-add-challenge',
  templateUrl: './add-challenge.component.html',
  styleUrls: ['./add-challenge.component.scss'],
  providers: [DatePipe]
})
export class AddChallengeComponent implements OnInit {

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

  public challengeNewSolution$: Observable<any>;
  public challengeNewSolution : any;
  public recipeId : any;
  public date : any;
  public challengeId : any;

  public solution$: Observable<any>;
  public solution: any;

  public Challenge : Object;
  public Name = "";
  public Ingredients_name = [];
  public Ingredients = [];
  public Solutions_Id = [];

  public AllSolutions : Object;

  public Ingredients_1 : Object;
  public ingredient : any;
  public SolutionsAll = [];

  public Categories = [{categories:"DINNER"},{categories:"DESERT"},{categories:"LUNCH"},{categories:"BREAKFAST"},{categories:"VEGETARIAN"},{categories:"VEGAN"}]
  public categories_Selected = [];

  public selectedIngredients = [];
  public ingredients_Recipe = [];
  public ingredient_backend = [];
  public steps = [];

  constructor(private datePipe : DatePipe,private formBuilder:FormBuilder,private route:ActivatedRoute, private challengeService : ChallengeService, private recipeService : RecipeService, public keycloak: KeycloakService,
    private ingredientService:IngredientService){

    this.quantityForm=formBuilder.group({
      quantity:this.quantity
    })

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

    this.ingredientService.getAllIngredientsResearch().subscribe(
      (data : Response) => {
        this.Ingredients_1 = data;
       });

    this.challengeId = +this.route.snapshot.paramMap.get('id'); 
    this.challengeService.getChallenge(this.challengeId).subscribe(
      (data : Response) => {
        this.Challenge = data;
        this.Name = data["name"];
        this.Ingredients = data["ingredients"];
        this.Solutions_Id = data["solutions"];

        this.Ingredients.forEach(element => {
          this.ingredientService.getIngredient(element.ingredientId).subscribe (
            (data : Response) => {
              this.ingredient = data;
              this.Ingredients_name.push(this.ingredient.name)
            }
          )

          //Get Favourite Recipes
          var recipe_sol_id = [];
          
          this.Solutions_Id.forEach(element => {
             recipe_sol_id.push(element.recipeId);
           });
          
          recipe_sol_id.forEach(element => {
             this.recipeService.getRecipe(element).subscribe(
               (data : Response) => {
                 //TODO use kafka to remove solutions when recipe is deleted
                 if (data != null){
                  this.SolutionsAll.push(data);
                 }
               }
             )
          });
        })
      }
    )
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

  addRecipe(){
    let Recipe: any = {};
    Recipe.name = (document.getElementById("nameRecipe") as HTMLInputElement).value;
    Recipe.authorID = this.keycloak.getID();
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
        this.addSolution(data)
        resolve();
      });
     
    });
    add.then(async () => {
      return  1
    });

  }

  async addSolution(solId){
    await this.challengeService.addSolution(this.challengeId,solId).toPromise();
    window.location.href = '/challenges/'+this.challengeId
  };

  async removeSolution(recipeId){
    await this.recipeService.deleteRecipe(recipeId).toPromise()
    window.location.href = '/challenges/'+this.challengeId
  }
    
     
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }


}
