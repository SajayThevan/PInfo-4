import { Component, OnInit } from '@angular/core';
import { TestService } from '../services/test.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  name:String = "";
  

  constructor(private testserive:TestService) {

   }

  action () {
    this.testserive.getcount
  }
  
  ngOnInit(): void {
    this.testserive.getInfo().subscribe( (data) => {
      this.name = data[0]["name"];
    });

     
  }


}
