import { Component, OnInit } from '@angular/core';
import { $ } from 'protractor';
import { summaryForJitFileName } from '@angular/compiler/src/aot/util';
import {ProfileService} from '../services/profile/profile.service'
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  constructor(private profileservice:ProfileService) { }

  

  name = "Sajay"
  lastname = "Thevan"
  points = 100

  profile = {
    name : "Sajay",
    lastname : "Thevan",
    points : 100 
  }

  recettes = [ 
    {
      name:'Cake',
      auteur:'Sajay',
      link : '/recipe'
    }
  ]

  favoris = [
    {
      name : 'Cake',
      auteur : 'Sajay'
    },
    {
      name:'MilkShake',
      auteur:'Luke'
    }
  ]

  frigo = [
    {
      name : 'Chocolat',
      quantity : '500 gr'
    },
    {
      name : 'Lait',
      quantity : '750 ml'
    },
    {
      name : 'Beurre',
      quantity : '200 gr'
    }
  ]

  profile1:any= []; 
  

  ngOnInit(): void {
  }

}


