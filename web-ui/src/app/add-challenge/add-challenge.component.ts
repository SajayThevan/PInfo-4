import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router'
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { RecipeService } from '../services/recipe/recipe.service';
import { ChallengeService } from '../services/challenge/challenge.service';
import { KeycloakInstance } from 'keycloak-js';
import { stringify } from 'querystring';
import { Observable, from } from 'rxjs';



@Component({
  selector: 'app-add-challenge',
  templateUrl: './add-challenge.component.html',
  styleUrls: ['./add-challenge.component.scss']
})
export class AddChallengeComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  public challengeNewSolution$: Observable<any>;
  public challengeNewSolution : any;
  public recipeId : any;
  public date : any;
  public challengeId : any;

  constructor(private route:ActivatedRoute, private challengeService : ChallengeService, private recipeService : RecipeService, public keycloak: KeycloakService){}


  ngOnInit(): void {
    this.challengeId = +this.route.snapshot.paramMap.get('id'); 
  }

  addRecipe(){
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
    let Recipe: any = {};
    Recipe.authorID = this.keycloak.getID();
    Recipe.name = "la mort 2";
    Recipe.date = "21/02/2020";
    this.date = new Date();
    Recipe.date = this.date.getDate() + '/' + ((this.date.getMonth() + 1)) + '/' + this.date.getFullYear();
    Recipe.imagePath ="tmp/image/recipe/"+stringify(Recipe.authorID)+".jpg"
    Recipe.ingredients = [{"quantite":2,"ingredientId":4}]
    Recipe.steps = [];
    Recipe.category = [{"categories":"LUNCH"}];
    Recipe.difficulty =3;
    Recipe.time = 1;
    Recipe.ratings = [];
    Recipe.comments = [];
    console.log(Recipe);
    return this.recipeService.createNewRecipe(Recipe);
  }

  addSolution(){
    // this.addRecipe().subscribe(
    //   (response: any) => {
    //     this.recipeId = response;
    //     console.log("recipe id : ");
    //     console.log(this.recipeId);
    //     console.log("challenge id : ");
    //     console.log(this.challengeId);
    //     console.log("challenge before add solution : ");
    //     this.challengeNewSolution$ = this.challengeService.getChallenge(this.challengeId);
    //     this.challengeNewSolution$.subscribe(
    //       (response : any) => {
    //         this.challengeNewSolution = response;
    //         console.log(this.challengeNewSolution);
    //       }
    //     )
    //     let ret = this.challengeService.addSolution(this.challengeId,this.recipeId);
    //     console.log("new challenge : ");
    //     this.challengeNewSolution$ = this.challengeService.getChallenge(this.challengeId);
    //     this.challengeNewSolution$.subscribe(
    //       (response : any) => {
    //         this.challengeNewSolution = response;
    //         console.log(this.challengeNewSolution);
    //       }
    //     )
    //   });
  }

  deleteRecipe(){
    let ret = this.recipeService.deleteRecipe(-1);
  }

}
