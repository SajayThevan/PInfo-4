import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  removeChallengebyID(id) {
    var Request_url = environment.challengeService.url + "/" + id;
    this.http.delete(Request_url);
  }

  // updateChallenge(id) PATH MISSING 

  // createChallenge(challenge) PATH MISSING

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
