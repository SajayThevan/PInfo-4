import { Component, OnInit,} from '@angular/core';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IngredientService } from '../services/ingredient/ingredient.service';
import {ingredient} from './interface';
import { element } from 'protractor';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  template: `
    <div class="example-config">
      Current value: {{value | json}}
    </div>
    <div class="example-wrapper">
      <p>Favorite sport:</p>
      <kendo-multiselect [data]="listItems" [(ngModel)]="value"></kendo-multiselect>
    </div>
  `
})
export class SearchComponent implements OnInit {

  constructor(private ingredientService : IngredientService) {
  }

  private Ingredients : ingredient[];
  private in:any[];

  ngOnInit() {
   this.ingredientService.getAllIngredientsResearch().subscribe(
     (data : ingredient[]) => {this.Ingredients = data}
   );
  }

  // INGREDIENT TO THIS FORMAT
  item = [
    {id : 1,name:'sasa'},
    {id : 2,name:'sdsa'}
  ];

  selected = [];

  Show() {
    console.log(this.Ingredients);
    console.log(this.selected);
  }
    

}