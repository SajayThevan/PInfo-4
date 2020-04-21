import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TendancesComponent } from './tendances/tendances.component';
import { HomeComponent } from './home/home.component';
import { MonthSpecialComponent } from './month-special/month-special.component';
import { ChallengesComponent } from './challenges/challenges.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { SearchComponent } from './search/search.component';


const routes: Routes = [
  { path : '',redirectTo:"/home",pathMatch:"full"},
  { path : 'home',component : HomeComponent},
  { path :'tendances', component : TendancesComponent},
  { path :'month', component : MonthSpecialComponent},
  { path :'challenge', component : ChallengesComponent},
  { path :'login_page', component : LoginPageComponent},
  { path :'profile', component : ProfilePageComponent},
  { path: 'search', component:SearchComponent}
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
  LoginPageComponent,
  ProfilePageComponent,
  SearchComponent
]
