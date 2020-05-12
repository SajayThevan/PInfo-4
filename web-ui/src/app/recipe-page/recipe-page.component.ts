import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe/recipe.service';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss']
})
export class RecipePageComponent implements OnInit {

  constructor(private recipeService : RecipeService) { }

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

  Difficulty = 0;
  Time = 0;

  Ratings = [];

  Comments = [];

  new_comment = "";

  ngOnInit(): void {
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
