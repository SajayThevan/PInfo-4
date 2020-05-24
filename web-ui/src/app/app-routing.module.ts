import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TendancesComponent } from './tendances/tendances.component';
import { HomeComponent } from './home/home.component';
import { MonthSpecialComponent } from './month-special/month-special.component';
import { ChallengesComponent } from './challenges/challenges.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SearchComponent } from './search/search.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { RecipePageComponent } from './recipe-page/recipe-page.component';
import { Browser } from 'protractor';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';


const routes: Routes = [
  { path: '',redirectTo:"/home",pathMatch:"full"},
  { path: 'home',component : HomeComponent},
  { path: 'tendances', component : TendancesComponent},
  { path: 'month', component : MonthSpecialComponent},
  { path: 'challenge', component : ChallengesComponent},
  { path: 'profile', component : ProfilePageComponent},
  { path: 'search', component:SearchComponent},
  { path: 'subscribe',component:SubscribeComponent},
  { path: 'recipe/:id', component:RecipePageComponent},
  { path: 'add_recipe', component:AddRecipeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [HomeComponent,
  TendancesComponent,
  MonthSpecialComponent,
  ChallengesComponent,
  ProfilePageComponent,
  SearchComponent,
  SubscribeComponent,
  RecipePageComponent
]
