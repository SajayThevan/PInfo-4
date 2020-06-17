import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { stringify } from 'querystring';
import { retry, catchError } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor( private http:HttpClient) {}

  profileExists(id) {
    return this.http.get<any>(environment.profileService.url + "/" + id + "/exists");
  }

  getProfile(id) {
    return this.http.get<any>(environment.profileService.url + "/" + id);
  }

  createProfile(profile) {
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
    return this.http.post(environment.profileService.url, JSON.stringify(profile), options)
  }

  // for fridge : 
  getIngredient(profileID) {
    var Request_url = environment.profileService.url + "/" + profileID + "/ingredients";
    return this.http.get(Request_url)
  }

  addIngredientById(profileID,ingredientID, quantity) {
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
    var Request_url = environment.profileService.url + "/" + profileID + "/ingredients?ingredient=" + ingredientID + "&quantity=" + quantity;
    this.http.post<any>(Request_url,null,options).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

  removeIngredient(profileID,ingredientID) {
    var Request_url = environment.profileService.url + "/" + profileID + "/ingredients?ingredient=" + ingredientID;
    this.http.delete(Request_url).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

  // for favourites : 
  getFavourite(profileID) {
    var Request_url = environment.profileService.url + "/" + profileID + "/favourites";
    return this.http.get(Request_url)
  }

  addFavouriteById(profileID, favouriteID) {
    var Request_url = environment.profileService.url + "/" + profileID + "/favourites?favourite=" + favouriteID;
    this.http.post(Request_url,null).subscribe({
      error: error => console.error('There was an error!', error)
    });
  }

  removeFavourite(profileID, favouriteID) {
    var Request_url = environment.profileService.url + "/" + profileID + "/favourites?favourite=" + favouriteID;
    this.http.delete(Request_url).subscribe({
      error: error => console.error('There was an error!', error)
    });
    return 1
  }

}
