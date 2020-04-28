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

  ingredients = ['Flour','Egg','Sugar','Vanilla'];
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
}
