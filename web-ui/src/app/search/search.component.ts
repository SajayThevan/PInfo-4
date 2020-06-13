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

  showResults : boolean;
  zeroResult : boolean;

  ngOnInit() {
   this.ingredientService.getAllIngredientsResearch().subscribe(
     (data : Response) => {
       this.Ingredients = data;
      });
    this.showResults = false;
    this.zeroResult = false;
  }

  selected = [];
  Result : any;
  url = "";

  

  getResult() {
    this.ngOnInit()
    if (this.selected.length > 0) {
      this.url = "";
    this.selected.forEach(element => {
      this.url = this.url+"?id="+element.id+"&"
    });
    this.url = this.url.substring(0, this.url.length - 1);
    this.recipeService.getSearchResult(this.url).subscribe(
      (data : Response) => {
        this.Result = data;
        if (this.Result.length > 0) {
          this.showResults = true;
        } else {
          this.zeroResult = true;
        }
        console.log(this.Result)
       });
    } else {
      console.log("Select Some ingredients")
    }

  } 
    
}
