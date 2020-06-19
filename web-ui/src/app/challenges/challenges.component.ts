import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule } from "@angular/forms";
import {Input, ChangeDetectionStrategy } from '@angular/core';
//import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import {BehaviorSubject, Observable} from 'rxjs';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { ProfileService } from '../services/profile/profile.service';
import { ChallengeService} from '../services/challenge/challenge.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';

@Component({
  selector: 'app-challenges',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.scss']
})

export class ChallengesComponent implements OnInit {

  public quantityForm:FormGroup;
  public quantity:FormControl;

  constructor(private challengeService:ChallengeService, private ingredientService:IngredientService, private profileService:ProfileService, public keycloak: KeycloakService) {}

  Challenges$ : Observable<any>;
  Challenges : any ;
  IDs = [];
  AuthorIDs = [];
  AuthorNames = [];
  Names = [];
  IngredientsIDs = [];
  IngredientsNames = [];
  Solutions = [];
  
  ngOnInit(): void {

    this.Challenges$ = this.challengeService.getAll()
    this.Challenges$.subscribe(
      (response:any ) =>{
        this.Challenges = response;
        this.Challenges.forEach(element => {
          this.IDs.push(element.id)
          this.AuthorIDs.push(element.authorID)
          this.Names.push(element.name)
          this.IngredientsIDs.push(element.ingredients)
          this.Solutions.push(element.solutions)
        });
        
         this.IngredientsIDs.forEach(element => {
          let IngredientsChallenge = []
          element.forEach(ing => {
            let IngredientsChallenge$ = this.ingredientService.getIngredient(ing.ingredientId)
            IngredientsChallenge$.subscribe(
              (response:any)=>{
                IngredientsChallenge.push(response.name)
              }
            )
          });
          this.IngredientsNames.push(IngredientsChallenge)
        });
        
      }
    )
  }
}

