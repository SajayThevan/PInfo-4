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

  public listItems: Array<string> = ['Baseball', 'Basketball', 'Cricket', 'Field Hockey', 'Football', 'Table Tennis', 'Tennis', 'Volleyball'];
  public value: any = [];
    

}