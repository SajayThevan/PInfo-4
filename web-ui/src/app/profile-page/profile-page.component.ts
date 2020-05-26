import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfileService } from '../services/profile/profile.service'
import { RecipeService } from '../services/recipe/recipe.service';
import { IngredientService } from '../services/ingredient/ingredient.service';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  constructor(private profileService: ProfileService, private recipeService: RecipeService, private ingredientService: IngredientService, public keycloak: KeycloakService) { }

  public profile$: Observable<any>;
  public recipes$: Observable<any>;
  public favourites$: Observable<any>;
  public fridge$: Observable<any>;
  public fridge: any; // TODO: Not the cleanest way, would be nicer to directly affect the reponse of the observable rather than assign it to a new variable

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      this.createProfile();
      // TODO Update profile
        // Check if profile has been updated through keycloak and sync with backend
      
      // this.profileService.profileExists(this.keycloak.getID()).subscribe(
      //   (profileExists : any) => {
      //     console.log(profileExists);
      //     if (!profileExists) {
      //       this.createProfile();
      //     }
      //     this.getProfileDetails();
      // });
    }
  }

  createProfile() {
    // TODO: DENIZ
      // Remove first/last name & email from profile model

    // let id = this.keycloak.getID();
    let id = 2;
    let pseudo = this.keycloak.getUsername();
    let score = 0;
    let favouriteRecipes = [];
    let fridgeContents = [];

    let profile: any = {};
    profile.id = id;
    profile.pseudo = pseudo;
    profile.email = 'fuck@gmail.com';
    profile.firstName = 'Deniz';
    profile.lastName = 'Sungurtekin';
    profile.score = score;
    profile.fridgeContents = fridgeContents;
    profile.favouriteRecipes = favouriteRecipes;

    console.log(profile);

    this.profileService.createProfile(profile);
  }

  getProfileDetails() {
    // Get Profile
    this.profile$ = this.profileService.getProfile(1)
    this.profile$.subscribe(
        (profile : any) => {
          // profile.firstName = this.keycloak.getFullName;
          
          // Get Fridge Contents
          var ingIDs = [];
          profile.fridgeContents.forEach(element => {
            ingIDs.push(element.ingredientId);
          });
          this.fridge$ = this.ingredientService.getIngredients(ingIDs);
          this.fridge$.subscribe(
            (response : any) => {
              this.fridge = response;
              for (let i = 0; i < this.fridge.length; i++) {
                this.fridge[i].quantity = profile.fridgeContents[i].quantity;
              };
          });
          // Get Favourite Recipes
          var recipeIDs = [];
          profile.favouriteRecipes.forEach(element => {
            recipeIDs.push(element.recipeId);
          });
          this.favourites$ = this.recipeService.getRecipes(recipeIDs);
    });
    // Get my recipes
    this.recipes$ = this.recipeService.getRecipeforProfile(1);
  }

  logout() {
    this.keycloak.logout();
  }
}
