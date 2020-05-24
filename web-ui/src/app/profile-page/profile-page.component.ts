import { Component, OnInit } from '@angular/core';
import { $ } from 'protractor';
import { summaryForJitFileName } from '@angular/compiler/src/aot/util';
import { first } from 'rxjs/operators';
import { ProfileService } from '../services/profile/profile.service'
import { RecipeService } from '../services/recipe/recipe.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  constructor(private profileService:ProfileService,private recipeService:RecipeService, public keycloak: KeycloakService) { }

  // name = "Sajay"
  // lastname = "Thevan"
  // points = 100
  //
  // profile = {
  //   name : "Sajay",
  //   lastname : "Thevan",
  //   points : 100
  // }
  //
  // recettes = [
  //   {
  //     name:'Cake',
  //     auteur:'Sajay',
  //     link : '/recipe/12'
  //   }
  // ]
  //
  // favoris = [
  //   {
  //     name : 'Cake',
  //     auteur : 'Sajay',
  //     link : '/recipe/12'
  //   },
  //   {
  //     name:'MilkShake',
  //     auteur:'Luke',
  //     link : '/recipe/12'
  //   }
  // ]
  //
  // frigo = [
  //   {
  //     name : 'Chocolat',
  //     quantity : '500 gr'
  //   },
  //   {
  //     name : 'Lait',
  //     quantity : '750 ml'
  //   },
  //   {
  //     name : 'Beurre',
  //     quantity : '200 gr'
  //   }
  // ]
  //
  // profile1:any= [];

  // public hasLoaded: Observable<any>;
  profile$: Observable<any>;
  public userAttributes: any;
  // public recipes : any;
  recipes = [
    {
      name:'Cake',
      auteur:'Sajay',
      link : '/recipe/12'
    }
  ]
  public favourites : any;
  public fridge : any;

  ngOnInit(): void {
    console.log('Profile');
    // this.keycloakAuth = this.keycloak.getKeycloakAuth();
    // this.keycloak.logout();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      console.log('Logged in');
      this.profile$ = this.profileService.getProfile(1);

      // // Contact API to obtain profile details
      // this.profileService.getProfile(1).subscribe(
      //   (data : Response) => {
      //     this.profile = data;
      //     console.log("Profile", data);
      //     this.hasLoaded = Fuck
      //     // Separate and call other services to get data
      // });
    }
  }

  logout() {
    this.keycloak.logout();
  }
}
