import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validator, Validators, ReactiveFormsModule } from "@angular/forms";
import {Input, ChangeDetectionStrategy } from '@angular/core';
//import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import {BehaviorSubject, Observable} from 'rxjs';
import { IngredientService } from '../services/ingredient/ingredient.service';

@Component({
  selector: 'app-challenges',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.scss']
})

export class ChallengesComponent implements OnInit {

  public quantityForm:FormGroup;
  public quantity:FormControl;

  constructor(private formBuilder:FormBuilder, private ingredientService : IngredientService) { 
    this.quantity=new FormControl('',[Validators.required])
    this.quantityForm=formBuilder.group({
    quantity:this.quantity
    })
  }

  Frigo = []
  FrigoInter = []
  Ingredients : Object
  Ingredient_Name = []
  
  //@Input() Frigo: Observable<any>;
  ngOnInit(): void {
    this.ingredientService.getAllIngredientsResearch().subscribe(
      (data : Response) => {
        this.Ingredients = data;
       });
       // TODO : Appel au backend
    this.Frigo = [
      {id:1,quantity : 10},
      {id:2,quantity : 20},
      {id:3,quantity : 30},
      {id:4,quantity : 40},
      {id:5,quantity : 50},
      {id:6,quantity : 60}
    ];
    this.FrigoInter = this.Frigo;
    // TODO : ID --> NOM
    this.Ingredient_Name = ['Lait','Beurre','Choco','Vanille','Pasta','Sucre']
  }
  // ------------- FRIDGE -------------------

  

  selected = [];

  // TODO 
  // Add modified elements to frige
  
  // Take quantity and assign to the correpondant ingredient

  Add(){
    console.log("Added")
    console.log(this.quantityForm.get('quantity').value)
    this.Frigo.push({
      id : this.selected[0].id,
      quantity : +this.quantityForm.get('quantity').value
    })
    this.Ingredient_Name.push(this.selected[0].name)
    // addIngredientById(profileID,ingredientID, quantity) 
    console.log(this.Frigo)
  }

  Remove(id){
    console.log("Removed")
    this.Frigo.splice(id,1)
    this.Ingredient_Name.splice(id,1)
    // removeIngredient(profileID,ingredientID)
    console.log(this.Frigo)
  }

  saveFridge(){
    // this.Frigo --> BACKEND
    this.Frigo = this.FrigoInter;
  }


  // ------------- END FRIDGE -------------------
}
