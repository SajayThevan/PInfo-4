
import { Component, OnInit, Input} from '@angular/core';
import { FormsModule, FormControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { element } from 'protractor';
import { stringify } from 'querystring';
import { RecipeService } from '../services/recipe/recipe.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],

})
export class SearchComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  constructor(private ingredientService : IngredientService,
        private recipeService : RecipeService,
        public keycloak: KeycloakService
    ) {}

  public Ingredients : Object;

  showResults : boolean;
  zeroResult : boolean;
  @Input() connected: boolean;

  ngOnInit() {
   this.ingredientService.getAllIngredientsResearch().subscribe(
     (data : Response) => {
       this.Ingredients = data;
      });
    this.showResults = false;
    this.zeroResult = false;

    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        console.log("NotConnected")
        this.connected = false;
    } else {
        console.log("Connected")
        this.connected = true;
    }
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
          // TODO : REDIRIGER TO CHALLENGE CREATE
        }
        console.log(this.Result)
       });
    } else {
      console.log("Select Some ingredients")
    }

  } 
    
}
