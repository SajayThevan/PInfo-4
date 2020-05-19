import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe/recipe.service';
import {ActivatedRoute} from '@angular/router'
import { IngredientService } from '../services/ingredient/ingredient.service';
import { stringify } from 'querystring';
@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss']
})
export class RecipePageComponent implements OnInit {

  constructor(private recipeService : RecipeService, private route:ActivatedRoute,
    private ingredientService : IngredientService) { }

  connected = true;

  Author_id = 0;
  Author_nom = "";
  Recipe_ID = 0;
  Recipe_name = "";
  Date = "";

  Ingredients = [];
  Ingredients_name = [];

  Steps = [];

  Category = [];
  Calorie = 0;

  Difficulty = 0;
  Time = 0;

  Ratings = [];

  Comments = [];

  new_comment = "";

  ngOnInit(): void {

    this.Recipe_ID = +this.route.snapshot.paramMap.get('id');

    this.recipeService.getRecipe(this.Recipe_ID).subscribe( (data) => {
      this.Recipe_name = data["name"];
      this.Author_id = data["authorID"];
      this.Date = data["date"];
      this.Ingredients = data["ingredients"];
      this.Steps = data["steps"];
      this.Category = data["category"];
      this.Difficulty = data["difficulty"];
      this.Time = data["time"];
      this.Ratings = data["ratings"]; // Take mean
      this.Comments = data["comments"];
    });

    let url : String= "";
    // synthaxe : /calories?id=1&id=2&id=4
    // Ingredient ID --> Ingredient Name
    for (var val of this.Ingredients) {
      this.ingredientService.getIngredient(val).subscribe( (data) => {
        this.Ingredients_name.push(data["name"]);
      });
      // Url for Compute Calories
      url = url + "id="+stringify(val)+"&"
    }

    // Removing the last &
    url = url.substring(0, url.length - 1);
    this.ingredientService.getComputeCalories(url).subscribe( (data) => {
      this.Calorie = data[0]; // ????
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
