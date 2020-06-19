import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule, routingComponent } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { MonthSpecialComponent } from './month-special/month-special.component';
import { ChallengesComponent } from './challenges/challenges.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SearchComponent } from './search/search.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { RecipePageComponent } from './recipe-page/recipe-page.component';

import { TestService } from './services/test.service';
import { ChallengeService } from './services/challenge/challenge.service';
import { IngredientService } from './services/ingredient/ingredient.service';
import { ProfileService } from './services/profile/profile.service';
import { RecipeService } from './services/recipe/recipe.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {NgSelectModule} from '@ng-select/ng-select'

import { APP_BASE_HREF } from '@angular/common';

import { AppInitService } from './app.init';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { AddChallengeComponent } from './add-challenge/add-challenge.component';
declare var window: any;

export function init_config(appLoadService: AppInitService, keycloak: KeycloakService) {
  return () =>  appLoadService.init().then( async () => {
    //  console.info(window.config);
     await keycloak.init();
    },
   );
}

@NgModule({
  declarations: [
    AppComponent,
    routingComponent,
    HomeComponent,
    MonthSpecialComponent,
    ChallengesComponent,
    ProfilePageComponent,
    SearchComponent,
    SubscribeComponent,
    RecipePageComponent,
    NotFoundComponent,
    AddChallengeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    NgSelectModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    TestService,
    ChallengeService,
    IngredientService,
    ProfileService,
    RecipeService,
    AppInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: init_config,
      deps: [AppInitService, KeycloakService],
      multi: true
    },
    KeycloakService,
    { provide: APP_BASE_HREF, useValue: '/' },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakInterceptorService,
      multi: true,
    },
    ],

  bootstrap: [AppComponent]
})
export class AppModule { }
