import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor( private http:HttpClient) {}

  profileExists(id) {
    return this.http.get<any>(environment.profileService.url + "/" + id + "/exists");
  }

  getProfile(token) {
    return this.http.get<any>(environment.profileService.url + "/" + token);
  }

  createProfile(profile): void {
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Accept': 'application/json'
    });
    let options = {
      headers: httpHeaders
    };
    this.http.post(environment.profileService.url, JSON.stringify(profile), options);
  }
}
