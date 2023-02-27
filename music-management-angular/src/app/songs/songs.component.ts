import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.css']
})
export class SongsComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  create() {
    this.router.navigate(['/song-new']);
  }
}
