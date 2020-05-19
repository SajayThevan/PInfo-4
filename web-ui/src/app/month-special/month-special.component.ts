import { Component, OnInit } from '@angular/core';
import { setupMaster } from 'cluster';

@Component({
  selector: 'app-month-special',
  templateUrl: './month-special.component.html',
  styleUrls: ['./month-special.component.scss']
})
export class MonthSpecialComponent implements OnInit {

  constructor() { 
    
  }

  ngOnInit(): void {
  }

  // Test set
  Month = [
    {id:1, recipename : 'Cake'},
    {id:2, recipename : 'Milkshake'},
    {id:3, recipename : 'Coffee'},
    {id:4, recipename : 'Milkshake'}
  ]
  
}
