import { Component, OnInit } from '@angular/core';
import { setupMaster } from 'cluster';
import { RecipeService } from '../services/recipe/recipe.service';
import { Observable, from } from 'rxjs';


@Component({
  selector: 'app-month-special',
  templateUrl: './month-special.component.html',
  styleUrls: ['./month-special.component.scss']
})
export class MonthSpecialComponent implements OnInit {

  constructor(private recipeService: RecipeService) { 
  }

  public recipesDTO$: Observable<any>;
  public month: any;

  ngOnInit(): void {
    this.recipesDTO$ = this.recipeService.getRecipeOfTheMonth();
    this.recipesDTO$.subscribe(
      (response : any) => {
        this.month = response;
    });
  }
  
}
