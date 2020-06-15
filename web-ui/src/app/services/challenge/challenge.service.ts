import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  constructor( private http:HttpClient) { }
  getInfo() {
    return this.http.get("https://jsonplaceholder.typicode.com/users");
  }

  getAll(){
    return this.http.get<any>(environment.challengeService.url);
  }

  getChallenge(id) {
    return this.http.get<any>(environment.challengeService.url + "/" + id);
  }

  getRecipesWithIngIds(ids) {
    return this.http.get<any>(environment.challengeService.url + "/ingredients/" + ids);
  }

  removeChallengebyID(id) {
    var Request_url = environment.challengeService.url + "/" + id;
    this.http.delete(Request_url);
  }

  updateChallenge(Challenge){
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
    return this.http.put(environment.challengeService.url, JSON.stringify(Challenge), options).subscribe({
      error: error => console.error('There was an error!', error)
    });
  } 

  createChallenge(Challenge) {
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
    return this.http.post(environment.challengeService.url, JSON.stringify(Challenge), options).subscribe({
      error: error => console.error('There was an error!', error)
    });
  }

  getIngredient(challengeID) {
    var Request_url = environment.challengeService.url + "/" + challengeID + "/ingredients";
    return this.http.get(Request_url);
  }

  getName(challengeID) {
    var Request_url = environment.challengeService.url + "/" + challengeID + "/name";
    return this.http.get(Request_url);
  }

  getAuthor(challengeID) {
    var Request_url = environment.challengeService.url + "/" + challengeID + "/author";
    return this.http.get(Request_url);
  }

  getSolution(challengeID) {
    var Request_url = environment.challengeService.url + "/" + challengeID + "/solutions";
    return this.http.get(Request_url);
  }

  addSolution(challengeID, recipeID) {
    var Request_url = environment.challengeService.url + "/" + challengeID + "/solutions?solution=" + recipeID;
    return this.http.post(Request_url, null);
  }

}
