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

  public Ingredients : Object;

  ngOnInit() {
   this.ingredientService.getAllIngredientsResearch().subscribe(
     (data : Response) => {
       this.Ingredients = data;
       console.log("Ingredients", data);
      });
  }

  selected = [];
}