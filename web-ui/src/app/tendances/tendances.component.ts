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

  // Test set
  Tendances = [
    {id:1, recipename : 'Cake'},
    {id:2, recipename : 'Milkshake'},
    {id:3, recipename : 'Coffee'},
    {id:4, recipename : 'Milkshake'},
    {id:5, recipename : 'Coffee'},
    {id:6, recipename : 'Pizza'},
    {id:7, recipename : 'Pasta'},
    {id:8, recipename : 'Brownie'},
    {id:9, recipename : 'Cake'}
  ]


}
