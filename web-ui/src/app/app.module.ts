import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule, routingComponent } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatIconModule} from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { MonthSpecialComponent } from './month-special/month-special.component';
import { ChallengesComponent } from './challenges/challenges.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SearchComponent } from './search/search.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { RecipePageComponent } from './recipe-page/recipe-page.component';

import {TestService} from './services/test.service';
import { ChallengeService } from './services/challenge/challenge.service';
import { IngredientService } from './services/ingredient/ingredient.service';
import { ProfileService } from './services/profile/profile.service';
import { RecipeService } from './services/recipe/recipe.service';

@NgModule({
  declarations: [
    AppComponent,
    routingComponent,
    HomeComponent,
    MonthSpecialComponent,
    ChallengesComponent,
    LoginPageComponent,
    ProfilePageComponent,
    SearchComponent,
    SubscribeComponent,
    RecipePageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [TestService,ChallengeService,IngredientService,ProfileService,RecipeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
