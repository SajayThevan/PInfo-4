import { Component, OnInit,} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { IngredientService } from '../services/ingredient/ingredient.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  constructor(private ingredientService : IngredientService) { }

  Ingredients:any = [];

  ngOnInit() {
    this.ingredientService.getAllIngredientsResearch().subscribe( (data) => {
      this.Ingredients = data;
    });
  }
    

}