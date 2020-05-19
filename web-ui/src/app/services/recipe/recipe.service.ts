import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { stringify } from 'querystring';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http:HttpClient) { }

  getRecipe(id) {
    return this.http.get("https://jsonplaceholder.typicode.com/users");
  }

  addComment(id,comment){
    var Request_url = environment.recipeService.url + "/addComment/"+stringify(id);
    this.http.put(Request_url,comment);
  }

  addRating (id,Rate){ // Check rate if 0<rate<10
    var Request_url = environment.recipeService.url + "/rate/"+stringify(id)+"/"+stringify(Rate);
    this.http.put(Request_url,null);
  }

  createNewRecipe(json) {
    var Request_url = environment.recipeService.url + "/create"
    this.http.post(Request_url,json);
  }

  deleteRecipe(id) {
    var Request_url = environment.recipeService.url + "/rm/"+stringify(id)
    this.http.delete(Request_url);
  }

  getRecipeforProfile(Profile_id) {
    var Request_url = environment.recipeService.url + "/recipesProfil/"+stringify(Profile_id)
    return this.http.get(Request_url); // [ RecipeID,RecipeName,Ingredients ]
  }

  getTendance(){
    var Request_url = environment.recipeService.url + "/best";
  }
}