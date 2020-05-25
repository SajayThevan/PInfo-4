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

  public profile$: Observable<any>;
  public userAttributes$: any;
  public recipes$: any;
  public favourites$: any;
  public fridge$: any;

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {

      // TODO
      // Decide method 1 or 2
      // Decide intefaces or use any (any will only work with observables)

      // METHOD 1: OBSERVABLES
      this.profile$ = this.profileService.getProfile(1)
      this.profile$.subscribe(
          (profile : Response) => {
            // this.hasLoaded = Promise.resolve(true);
            // this

            // TODO
            // Call other services to get fridge contents & favourite recipes
  
            // TODO: Backend
            // Ingredient Service: Function that accepts a list of ingredient id's and returns the minimum info for the ingredient (name, id)
            // Recipe Service: Function that accepts a list of recipe id's and returns the minimum info for the recipe (name, etc...)
  
            //this.ingredientService.getIngredients()
      });

      // Get my recipes
      this.recipes$ = this.recipeService.getRecipeforProfile(1);
      this.recipes$.subscribe(
        (recipe : Response) => {
          console.log(recipe);
      });
    }
  }

  logout() {
    this.keycloak.logout();
  }
}
