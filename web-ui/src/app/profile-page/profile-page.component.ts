import { Component, OnInit } from '@angular/core';
import { Observable, from } from 'rxjs';
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
                      // Done like this as quantities are added to fridge and not sure how to add elements to the observable response

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      this.profileService.profileExists(this.keycloak.getID()).subscribe(
        (profileExists : any) => {
          if (!profileExists) {
            this.createProfile().subscribe(
              (reponse: any) => {
                this.getProfileDetails()
            });
          } else {
            this.getProfileDetails();
          }
      });
    }
  }

  createProfile() {
    let profile: any = {};
    profile.id = this.keycloak.getID();
    profile.pseudo = this.keycloak.getUsername();
    profile.email = this.keycloak.getEmail();
    profile.firstName = this.keycloak.getFirstName();
    profile.lastName = this.keycloak.getLastName();
    profile.score = 0;
    profile.fridgeContents = [];
    profile.favouriteRecipes = [];
    return this.profileService.createProfile(profile);
  }

  getProfileDetails() {
    // Get Profile
    this.profile$ = this.profileService.getProfile(this.keycloak.getID())
    this.profile$.subscribe(
        (profile : any) => {          
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
    this.recipes$ = this.recipeService.getRecipeforProfile(this.keycloak.getID());
  }

  logout() {
    this.keycloak.logout();
  }
}
