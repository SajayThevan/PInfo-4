import { Component, OnInit } from '@angular/core';
import { $ } from 'protractor';
import { summaryForJitFileName } from '@angular/compiler/src/aot/util';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

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
      auteur:'Sajay'
    },
    {
      name:'Brownie',
      auteur:'Ella'
    },
    {
      name:'Pizza',
      auteur:'Deniz'
    },
    {
      name:'Juice',
      auteur:'Mathias'
    },
    {
      name:'MilkShake',
      auteur:'Luke'
    }
  ]

}


