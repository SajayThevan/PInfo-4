import { Component, OnInit,} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IngredientService } from '../services/ingredient/ingredient.service';

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

  constructor(private ingredientService : IngredientService) { }

  Ingredients:any = [];

  ngOnInit() {
    this.ingredientService.getAllIngredientsResearch().subscribe( (data) => {
      this.Ingredients = data;
    });
  }

  items = [
    {id: 1, name: 'Chocolate'},
    {id: 2, name: 'Butter'},
    {id: 3, name: 'Milk'},
    {id: 4, name: 'Flour'},
    {id: 5, name: 'Vanilla'}
  ];

  selected = [];

  Show() {
    console.log(this.selected);
  }
    

}