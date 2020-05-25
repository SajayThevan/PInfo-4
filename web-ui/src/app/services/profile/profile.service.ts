import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor( private http:HttpClient) {}

  getProfile(token) {
    console.log(token);
    return this.http.get<any>(environment.profileService.url + "/" + token);
  }
}
