import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe/recipe.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { DatePipe } from '@angular/common';
import { stringify } from 'querystring';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-challenge',
  templateUrl: './add-challenge.component.html',
  styleUrls: ['./add-challenge.component.scss']
})
export class AddChallengeComponent implements OnInit {
  public keycloakAuth: KeycloakInstance;

  constructor(private route:ActivatedRoute, private recipeService: RecipeService, public keycloak: KeycloakService,private datePipe: DatePipe) {
  }

  ChallengeId = 0;
  ngOnInit(): void {
    this.ChallengeId = +this.route.snapshot.paramMap.get('id'); 
  }

  addSolution() {
    /*
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
    */
   /*
   let Recipe: any = {};
    Recipe.authorID = this.keycloak.getID();
    Recipe.name = "Gaucamole";
    Recipe.date = "21/02/2020";
    Recipe.imagePath ="tmp/image/recipe/"+stringify(Recipe.authorID)+".jpg"
    Recipe.ingredients = [{"id":1,"quantite":2,"ingredientId":4}]
    Recipe.steps = [];
    Recipe.category = [];
    Recipe.difficulty =3;
    Recipe.time = 1;
    Recipe.ratings = [];
    Recipe.comments = [];
    console.log(Recipe)
    let ret = this.recipeService.createNewRecipe(Recipe)
    console.log("here return");
    console.log(ret)
    */
  }

}
