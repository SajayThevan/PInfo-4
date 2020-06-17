import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { stringify } from 'querystring';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http:HttpClient) { }

  getRecipe(id) {
    return this.http.get(environment.recipeService.url +"/"+id);
  }

  getRecipes(idList) {
    // Convert idList to ?id=4&id=2&id=1
    var queryList = "?";
    idList.forEach(id => {
      queryList = queryList + "id=" + id + "&";
    });
    queryList = queryList.substring(0, queryList.length - 1); // Remove last &
    return this.http.get(environment.recipeService.url + "/" + queryList);
  }

  addComment(id,comment){
  const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    var Request_url = environment.recipeService.url +"/"+id+"/comments";
    this.http.post(Request_url, comment, { headers, responseType: 'text'}).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

  addRating (id,rate){ // Check rate if 0<rate<10
    var Request_url = environment.recipeService.url +"/"+id+"/rate?rate="+rate;
    this.http.put(Request_url,null).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

  createNewRecipe(recipe) {
    var Request_url = environment.recipeService.url;
    console.log(Request_url)
    console.log(JSON.stringify(recipe))

    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
   return this.http.post(Request_url, JSON.stringify(recipe), options).subscribe({
    error: error => console.error('There was an error!', error)
  });
  }

  deleteRecipe(id) {
    var Request_url = environment.recipeService.url+"/"+id;
    this.http.delete(Request_url).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

  getRecipeforProfile(profile_id) {
    var Request_url = environment.recipeService.url + "/profiles/" + profile_id;
    return this.http.get(Request_url); // [ RecipeID,RecipeName,Ingredients ]
  }

  getTrends(){
    var Request_url = environment.recipeService.url + "/trends";
    return this.http.get(Request_url)
  }

  getRecipeOfTheMonth(){
    var Request_url = environment.recipeService.url + "/recipe-of-the-month";
    return this.http.get(Request_url)
  }

  getSearchResult(url){
    var Request_url = environment.recipeService.url + "/ingredients" + url; // To complete
    return this.http.get(Request_url)
  }
}