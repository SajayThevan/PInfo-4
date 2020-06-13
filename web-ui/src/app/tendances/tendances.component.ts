import { Component, OnInit } from '@angular/core';
import { stringify } from 'querystring';
import { RecipeService } from '../services/recipe/recipe.service';
import { Observable, from } from 'rxjs';


@Component({
  selector: 'app-tendances',
  templateUrl: './tendances.component.html',
  styleUrls: ['./tendances.component.scss']
})
export class TendancesComponent implements OnInit {

  constructor(private recipeService: RecipeService) { }

  public recipesDTO$: Observable<any>;
  public tendances: any;

  ngOnInit(): void {
    this.recipesDTO$ = this.recipeService.getTrends();
    this.recipesDTO$.subscribe(
      (response : any) => {
        this.tendances = response;
        console.log(this.tendances)
    });
  }

}
