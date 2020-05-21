import { Component, OnInit,} from '@angular/core';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { element } from 'protractor';
import { stringify } from 'querystring';
import { RecipeService } from '../services/recipe/recipe.service';


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

  constructor(private ingredientService : IngredientService,
        private recipeService : RecipeService
    ) {
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
  url = "";
  public Result : Object;

  getResult() {
    console.log(this.selected);
    this.selected.forEach(element => {
      this.url = this.url+"?id="+element.id+"&"
    });
    this.url = this.url.substring(0, this.url.length - 1);
    console.log(this.url);
    this.recipeService.getSearchResult(this.url).subscribe(
      (data : Response) => {
        this.Result = data;
        console.log("Search Result", data);
       });
  }
}
