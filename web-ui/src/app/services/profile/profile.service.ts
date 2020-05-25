import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { stringify } from 'querystring';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Profile } from '../../profile-page/profile-interfaces';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor( private http:HttpClient) {}

  getProfile(token) {
    console.log(token);
    return this.http.get<Object>(environment.profileService.url + "/" + token);
  }
}
