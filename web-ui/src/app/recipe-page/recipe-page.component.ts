import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe/recipe.service';
import {ActivatedRoute} from '@angular/router'
import { IngredientService } from '../services/ingredient/ingredient.service';
import { stringify } from 'querystring';
import { ProfileService } from '../services/profile/profile.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss']
})
export class RecipePageComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  constructor(private recipeService : RecipeService, private route:ActivatedRoute,
    private ingredientService : IngredientService, private profileService: ProfileService,public keycloak: KeycloakService) { }

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

  public Recipe : Object;
  public ingredient : any;

  ngOnInit(): void {

    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        console.log("NotConnected")
        this.connected = false;
    } else {
        console.log("Connected")
        this.connected = true;
    }

    this.Recipe_ID = +this.route.snapshot.paramMap.get('id');

    this.recipeService.getRecipe(this.Recipe_ID).subscribe( (data : Response) => {
      this.Recipe = data;
      console.log(this.Recipe)
      this.Recipe_name = data["name"];
      this.Author_id = data["authorID"];
      this.Date = data["date"];
      this.Ingredients = data["ingredients"];

      console.log(this.Ingredients)

      this.Ingredients.forEach(element => {
        console.log(element.ingredientID)
        this.ingredientService.getIngredient(element.ingredientID).subscribe (
          (data : Response) => {
            this.ingredient = data;
            this.Ingredients_name.push(this.ingredient.name)
          }
        )
      });

      this.Steps_O = data["steps"];
      this.Steps_O.forEach(element => {
        this.Steps.push(element.steps)
      });


      this.Category = data["category"];
      this.Difficulty = data["difficulty"];
      this.Time = data["time"];
      this.Ratings = data["ratings"]; // Take mean
      this.Ratings.forEach(element => {
        this.mean = this.mean + element.rate
      });
      this.mean = this.mean/this.Ratings.length;
      this.Comments = data["comments"]
    });

    let url : String= "";
    // synthaxe : /calories?id=1&id=2&id=4
    // Ingredient ID --> Ingredient Name
    

    console.log(this.Ingredients_name)

    // Removing the last &
    url = url.substring(0, url.length - 1);
    this.ingredientService.getComputeCalories(url).subscribe( (data) => {
      this.Calorie = data[0]; // ????
    });
    this.ingredientService.getComputeFat(url).subscribe( (data) => {
      this.Fat = data[0]; // ????
    });
    this.ingredientService.getComputeCholesterol(url).subscribe( (data) => {
      this.Cholesterol = data[0]; // ????
    });
    this.ingredientService.getComputeProteins(url).subscribe( (data) => {
      this.Proteins = data[0]; // ????
    });
    this.ingredientService.getComputeSalt(url).subscribe( (data) => {
      this.Salt = data[0]; // ????
    });

  }



  // TEST
  info = ['Cake','Sajay',9,7];

  ingredients = [
    {
      name: 'Flour',
      disp : 'black'
    },
    {
      name: 'Egg',
      disp : 'black'
    },
    {
      name: 'Sugar',
      disp : 'black'
    },
    {
      name: 'Vanilla',
      disp : 'black'
    }
  ];

  steps = ['Add Flour','Add Egg','Add Sugar','Add Vanilla'];
  comments = [
    {
      name : 'Luke',
      comment : 'blablablaaa'
    },
    {
      name : 'Deniz',
      comment : 'blaablaablaa'
    }
  ];

  addRating () {
    let note = 5;
    this.recipeService.addRating(this.Recipe_ID,note);
  }
  addFav(){
    console.log(this.keycloak.getID())
    console.log(this.Recipe_ID)
    this.profileService.addFavouriteById(this.keycloak.getID(),this.Recipe_ID);
  }

  addComment () {
    this.recipeService.addComment(this.Recipe_ID,this.new_comment)
  }

  check() {
    this.ingredients[0].disp = 'green';
    this.ingredients[1].disp = 'red';
    this.ingredients[2].disp = 'red';
    this.ingredients[3].disp = 'red';
  }
}
