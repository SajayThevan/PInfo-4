import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe/recipe.service';
import {ActivatedRoute} from '@angular/router'
import { IngredientService } from '../services/ingredient/ingredient.service';
import { stringify } from 'querystring';
import { ProfileService } from '../services/profile/profile.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { AddChallengeComponent } from '../add-challenge/add-challenge.component';

@Component({
    selector: 'app-recipe-page',
    templateUrl: './recipe-page.component.html',
    styleUrls: ['./recipe-page.component.scss']
  })
  export class RecipePageComponent implements OnInit {
  
    public keycloakAuth: KeycloakInstance;
    public recipeForm: FormGroup;
    public comment: FormControl;
    public note : FormControl;
  
    constructor(private formBuilder: FormBuilder, private recipeService : RecipeService, private route:ActivatedRoute, private ingredientService : IngredientService, private profileService: ProfileService,public keycloak: KeycloakService) {
      this.comment = new FormControl('',[Validators.required]);
      this.note = new FormControl('',[Validators.required,Validators.pattern(/^-?(0|[1-9]\d*)?$/)]);
      this.recipeForm = formBuilder.group({
        comment: this.comment,
        note:this.note
        }); 
    }
  
    connected : boolean;
  
    Author_id = 0;
    Author_nom = "";
    Recipe_ID = 0;
    Recipe_name = "";
    Date = "";
  
    Ingredients = [];
    Ingredients_name = [];
  
    Steps_O = [];
    Steps = [];
  
    Category = [];
    Calorie = 0;
    Fat = 0;
    Cholesterol = 0;
    Proteins = 0;
    Salt = 0;
  
    
  
    Difficulty = 0;
    Time = 0;
  
    Ratings = [];
    mean = 0;
  
    Comments = [];
  
    new_comment = "";
    rate = 0;
  
    public Recipe : Object;
    public ingredient : any;
    public authorName: "Anonyme";
  
    ngOnInit(): void {
  
      this.keycloakAuth = this.keycloak.getKeycloakAuth();
      if (this.keycloak.isLoggedIn() === false) {
          this.connected = false;
      } else {
          this.connected = true;
      }
  
      this.Recipe_ID = +this.route.snapshot.paramMap.get('id');
      this.Ingredients_name = []
      this.Steps = [];
      this.recipeService.getRecipe(this.Recipe_ID).subscribe( (data : Response) => {
        this.Recipe = data;
        this.Recipe_name = data["name"];
        this.Author_id = data["authorID"];
        this.profileService.getProfile(this.Author_id).subscribe( (name: Response) =>{
          this.authorName = name["pseudo"];
        });
        this.Date = data["date"];
        this.Ingredients = data["ingredients"];
        console.log(this.Ingredients)

        let url : String= "/calories?";

        this.Ingredients.forEach(element => {
          url = url+"&id="+ element.ingredientId;
          this.ingredientService.getIngredient(element.ingredientId).subscribe (
            (data : Response) => {
              this.ingredient = data;
              this.Ingredients_name.push({name :this.ingredient.name, quantite : element.quantite})
            }
          )
        });

        //console.log(url)

        this.ingredientService.getComputeCalories(url).subscribe( (data : Response) => {
          this.Calorie = +data;
        });
        this.ingredientService.getComputeFat(url).subscribe( (data : Response) => {
          this.Fat = +data;
        });
        this.ingredientService.getComputeCholesterol(url).subscribe( (data : Response) => {
          this.Cholesterol = +data;
        });
        this.ingredientService.getComputeProteins(url).subscribe( (data : Response) => {
          this.Proteins = +data;
        });
        this.ingredientService.getComputeSalt(url).subscribe( (data : Response) => {
          this.Salt = +data;
        });

  
        this.Steps_O = data["steps"];
        this.Steps_O.forEach(element => {
          this.Steps.push(element.step)
        });
  
        this.Category = data["category"];
        this.Difficulty = data["difficulty"];
        this.Time = data["time"];
        this.Ratings = data["ratings"]; // Take mean
        this.Ratings.forEach(element => {
          this.mean = this.mean + element.rate
        });
        this.mean = +(this.mean/this.Ratings.length).toFixed(2);
        this.Comments = data["comments"]
      });
  
      let url : String= "";
      // synthaxe : /calories?id=1&id=2&id=4
      // Ingredient ID --> Ingredient Name
    
      // Removing the last &
      url = url.substring(0, url.length - 1);
      
    }
  
    async addRating() {
      this.rate = +this.recipeForm.get("note").value;
      console.log(this.rate)
      await this.recipeService.addRating(this.Recipe_ID,this.rate).toPromise();
      this.recipeForm.reset();
      this.ngOnInit()
    }

    addFav(){
      this.profileService.addFavouriteById(this.keycloak.getID(),this.Recipe_ID);
    }
  
    async addComment() {
      this.new_comment = this.recipeForm.get("comment").value;
      await this.recipeService.addComment(this.Recipe_ID,this.new_comment).toPromise();
      this.recipeForm.reset();
      this.ngOnInit()
    }
  

    async delay(ms: number) {
      await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
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
  