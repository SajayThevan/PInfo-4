import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { stringify } from 'querystring';

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
    return this.http.get("https://pinfo4.unige.ch/ingredient/"+stringify(id));
  }

  // pour la recherche
  getAllIngredientsResearch() {
    return this.http.get("https://pinfo4.unige.ch/ingredient/research");
  }

  // synthaxe : /computeCalories?id=1&id=2&id=4
  // on prendra les ids des ingredients de la recette a afficher
  getComputeCalories(idList) {
    return this.http.get("https://pinfo4.unige.ch/ingredient/computeCalories"+stringify(idList));
  }
}