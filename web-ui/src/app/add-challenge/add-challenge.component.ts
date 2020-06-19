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

  public quantite1:FormControl;
  public quantite2:FormControl;
  public quantite3:FormControl;
  public quantite4:FormControl;
  public quantite5:FormControl;
  public quantite6:FormControl;
  public quantite7:FormControl;
  public quantite8:FormControl;
  public quantite9:FormControl;
  public quantite10:FormControl;

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

  public obligatoryIng : any;

  constructor(private datePipe : DatePipe,private formBuilder:FormBuilder,private route:ActivatedRoute, private challengeService : ChallengeService, private recipeService : RecipeService, public keycloak: KeycloakService,
    private ingredientService:IngredientService){

    this.quantityForm=formBuilder.group({
      quantity:this.quantity,
      quantite1 : this.quantite1,
      quantite2 : this.quantite2,
      quantite3 : this.quantite3,
      quantite4 : this.quantite4,
      quantite5 : this.quantite5,
      quantite6 : this.quantite6,
      quantite7 : this.quantite7,
      quantite8 : this.quantite8,
      quantite9 : this.quantite9,
      quantite10 : this.quantite10
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

  list = ["quantite1","quantite2","quantite3",
  "quantite4","quantite5",
  "quantite6","quantite7","quantite8","quantite9",
  "quantite10"]

  connected : boolean ;
  rights : boolean ;
  AuthorID = "";


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
        this.AuthorID = data["authorID"];
        this.keycloakAuth = this.keycloak.getKeycloakAuth();
        if (this.keycloak.isLoggedIn() === false) {
          this.connected = false;
        } else {
          this.connected = true;
          if (this.keycloak.getID() == this.AuthorID){
              this.rights = true;
          } else {
              this.rights = false;
          }

    }

        this.Ingredients.forEach(element => {
          this.ingredientService.getIngredient(element.ingredientsId).subscribe (
            (data : Response) => {
              this.ingredient = data;
              this.Ingredients_name.push(this.ingredient.name)
              this.ingredients_Recipe.push({name :this.ingredient.name,quantite : 0});
            }
          )
        })

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
      }
    )

    
  }

  

  addIngredient(){
     if(this.selectedIngredients.length > 0) {
        let quantity = +(document.getElementById("quantity") as HTMLInputElement).value;
        this.ingredients_Recipe.push({name : this.selectedIngredients[0].name , quantite : quantity})
        this.ingredient_backend.push({quantite:quantity ,ingredientId:this.selectedIngredients[0].id})
        this.selectedIngredients = [];
     } 
  }

  addSteps(){
    this.steps.push({step:(document.getElementById("instruction") as HTMLInputElement).value})
  }

  showInput = true;

  addRecipe(){
    let Recipe: any = {};
    Recipe.name = (document.getElementById("nameRecipe") as HTMLInputElement).value;
    Recipe.authorID = this.keycloak.getID();
    Recipe.date = this.datePipe.transform(new Date(), 'dd/MM/yyyy');
    Recipe.imagePath = "/tmp/images/logo.png";
    Recipe.ingredients = this.ingredient_backend;
    this.Ingredients.forEach((element,index) => {
      Recipe.ingredients.push({"quantite": +(document.getElementById(this.list[index]) as HTMLInputElement).value
      ,"ingredientId": element["ingredientsId"]});
    })
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

  handleKeyPressQuantity(e) {
    var code = (e.which) ? e.which : e.keyCode;
    let val = e.target.value.split('');
    let countDot = val.filter((v) => (v === '.')).length;
    if (code == 46 && countDot == 0){
      return true;
    }
    if (code > 31 && (code < 48 || code > 57)) {
        e.preventDefault();
    }
  }

  handleKeyPressTime(e) {
    var code = (e.which) ? e.which : e.keyCode;
    let val = e.target.value.split('');
    let countDot = val.filter((v) => (v === '.')).length;
    if (code == 46 && countDot == 0){
      return true;
    }
    if (code > 31 && (code < 48 || code > 57)) {
        e.preventDefault();
    }
  }

  handleKeyPressDifficulty(e) {
    var code = (e.which) ? e.which : e.keyCode;
    let val = e.target.value.split('');
    let num = +String(e.target.value).concat(e.key);
    let countDot = val.filter((v) => (v === '.')).length;
    if (code == 46 && countDot == 0) {
      return true
    }
    if (code > 31 && (code < 48 || code > 57) ) {
      e.preventDefault();
    } else if (num>10) {
      e.preventDefault();
    }
  }


}
