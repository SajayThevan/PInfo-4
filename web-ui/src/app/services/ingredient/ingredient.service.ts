import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { stringify } from 'querystring';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  constructor( private http:HttpClient) { }
  getInfo() {
    return this.http.get("https://jsonplaceholder.typicode.com/users");
  }

  // recupere ingredient pour affichages des recettes
  getIngredient(id) {
    return this.http.get(environment.ingredientService.url + "/" + stringify(id));
  }

  // pour la recherche
  getAllIngredientsResearch() {
    return this.http.get(environment.ingredientService.url + "/names");
  }

  // synthaxe : /calories?id=1&id=2&id=4
  // on prendra les ids des ingredients de la recette a afficher
  getComputeCalories(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/calories?"+stringify(idList));
  }

  getComputeFat(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/fat?"+stringify(idList));
  }

  getComputeCholesterol(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/cholesterol?"+stringify(idList));
  }

  getComputeProteins(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/proteins?"+stringify(idList));
  }

  getComputeSalt(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/salt?"+stringify(idList));
  }
}
