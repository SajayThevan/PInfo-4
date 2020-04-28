import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss']
})
export class RecipePageComponent implements OnInit {

  constructor() { }

  connected = true;

  ngOnInit(): void {
  }

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

  check() {
    this.ingredients[0].disp = 'green';
    this.ingredients[1].disp = 'red';
    this.ingredients[2].disp = 'red';
    this.ingredients[3].disp = 'red';
  }
}
