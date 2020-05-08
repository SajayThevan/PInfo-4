import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor( private http:HttpClient) { }
  getName() {
    return this.http.get("https://jsonplaceholder.typicode.com/users");
  }
}
