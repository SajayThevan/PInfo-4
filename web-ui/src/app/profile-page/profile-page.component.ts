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
  public userAttributes$: any;
  public recipes$: any;
  public favourites$: any;
  public fridge$: any;

  ngOnInit(): void {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
        this.keycloak.login();
    } else {
      // TODO: Delete interfaces
      // TODO: Secure backend
      // Get Profile
      this.profile$ = this.profileService.getProfile(1)
      this.profile$.subscribe(
          (profile : any) => {

            var ingIDs = [];
            profile.fridgeContents.forEach(element => {
              ingIDs.push(element.ingredientId);
            });
            this.fridge$ = this.ingredientService.getIngredients(ingIDs);
            this.fridge$.subscribe(
              (fridge : any) => {
                console.log('Fridge', fridge);
                // TODO: Link quantities
                
            });

            var recipeIDs = [];
            profile.favouriteRecipes.forEach(element => {
              recipeIDs.push(element.recipeId);
            });
            this.favourites$ = this.recipeService.getRecipes(recipeIDs);
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
