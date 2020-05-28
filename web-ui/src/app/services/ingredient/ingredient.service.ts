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
    return this.http.get(environment.ingredientService.url + "/" + id);
  }

  getIngredients(idList) {
    // Convert idList to ?id=4&id=2&id=1
    var queryList = "?";
    idList.forEach(id => {
      queryList = queryList + "id=" + id + "&";
    });
    queryList = queryList.substring(0, queryList.length - 1); // Remove last &
    return this.http.get(environment.ingredientService.url + "/selected" + queryList);
  }

  // pour la recherche
  getAllIngredientsResearch() {
    return this.http.get(environment.ingredientService.url + "/names");
  }

  // synthaxe : /calories?id=1&id=2&id=4
  // on prendra les ids des ingredients de la recette a afficher
  getComputeCalories(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/calories?" + idList);
  }

  getComputeFat(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/fat?" + idList);
  }

  getComputeCholesterol(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/cholesterol?" + idList);
  }

  getComputeProteins(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/proteins?" + idList);
  }

  getComputeSalt(idList) {
    // TODO: DISCUSS COMPUTE CALORIES HOW THE FUNCTION GETS THE INGREDIENTS
    return this.http.get(environment.ingredientService.url + "/salt?" + idList);
  }
}
