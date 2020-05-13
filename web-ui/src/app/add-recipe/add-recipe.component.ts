import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.scss']
})
export class AddRecipeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  Ingredient = [];


  addIngredient() {
    this.Ingredient.push('hu');
  }

}
