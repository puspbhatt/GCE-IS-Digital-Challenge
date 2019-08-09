import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  
  constructor(private router: Router){
	this.title = 'Welcome to Shop Manager';
	 this.router.navigate(['/shops']);
	
 } 
}
