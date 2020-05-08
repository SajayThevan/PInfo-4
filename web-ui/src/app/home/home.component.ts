import { Component, OnInit } from '@angular/core';
import { TestService } from '../services/test.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  name:any= [];

  constructor(private testserive:TestService) {

   }

  
  ngOnInit(): void {
    this.testserive.getInfo().subscribe( (data) => {
      this.name = data;
    });
  }


}
