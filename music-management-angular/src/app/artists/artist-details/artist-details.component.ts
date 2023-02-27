import {Component, Inject, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs";
import {Location} from "@angular/common";
import {Artist} from "../shared/artist.model";
import {ArtistService} from "../shared/artist.service";
import {ArtistResponse} from "../shared/artistResponse.model";

@Component({
  selector: 'app-artist-details',
  templateUrl: './artist-details.component.html',
  styleUrls: ['./artist-details.component.css']
})
export class ArtistDetailsComponent implements OnInit {
  @Input() artist!: Artist;

  constructor(private route: ActivatedRoute, @Inject(ArtistService) private artistService: ArtistService, private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.artistService.getArtistById(+params['id'])))
      .subscribe({
        next: (artistResponse: ArtistResponse) => {
          this.artist = artistResponse.artists.at(0) ?? <Artist>{}
        }
      });
  }

  goBack() {
    this.location.back();
  }

  save() {
    this.artistService.update(this.artist).subscribe({
      next: (artistResponse: ArtistResponse) => {
        this.goBack();
      }
    })
  }
}
