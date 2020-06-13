import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule } from "@angular/forms";
import {Input, ChangeDetectionStrategy } from '@angular/core';
//import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import {BehaviorSubject, Observable} from 'rxjs';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { ChallengeService} from '../services/challenge/challenge.service';

@Component({
  selector: 'app-challenges',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.scss']
})

export class ChallengesComponent implements OnInit {

  public quantityForm:FormGroup;
  public quantity:FormControl;

  constructor(private challengeService:ChallengeService) {}

  Challenges$ : Observable<any>;
  Challenges : any ;
  
  ngOnInit(): void {

    this.Challenges$ = this.challengeService.getAll()
    this.Challenges$.subscribe(
      (response:any ) =>{
        this.Challenges = response;
        console.log(this.Challenges);
      }
    )
  }
}
