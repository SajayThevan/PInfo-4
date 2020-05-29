import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.scss']
})
export class ChallengesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  // ------------- FRIDGE -------------------

  Frigo = [
    {id:1,quantity : 10},
    {id:2,quantity : 20},
    {id:3,quantity : 30},
    {id:4,quantity : 40},
    {id:5,quantity : 50},
    {id:6,quantity : 60}
  ];

  Ingredient_Name = ['Lait','Beurre','Choco','Vanille','Pasta','Sucre']

  Ingredients = [
    {id:7,name:'Ketchup'},
    {id:8,name:'Soja'},
    {id:9,name:'Pasta'},
    {id:10,name:'Almond'}
  ];

  selected = [];

  // TODO 
  // Add modified elements to frige
  // Take quantity and assign to the correpondant ingredient

  Add(){
    console.log("Added")
  }

  Remove(i){
    console.log("Removed")
  }

  // ------------- END FRIDGE -------------------
}
