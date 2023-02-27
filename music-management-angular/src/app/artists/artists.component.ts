import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-artists',
  templateUrl: './artists.component.html',
  styleUrls: ['./artists.component.css']
})
export class ArtistsComponent implements OnInit{

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  create() {
    this.router.navigate(['/artist-new'])

  }
}
