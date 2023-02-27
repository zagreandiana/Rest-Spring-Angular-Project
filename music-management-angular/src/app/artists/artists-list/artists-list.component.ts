import {Component, Inject, OnInit} from '@angular/core';
import {Artist} from "../shared/artist.model";
import {ArtistService} from "../shared/artist.service";
import {ArtistResponse} from "../shared/artistResponse.model";
import {Router} from "@angular/router";


@Component({
  selector: 'app-artists-list',
  templateUrl: './artists-list.component.html',
  styleUrls: ['./artists-list.component.css']
})
export class ArtistsListComponent implements OnInit {
  // errorMessage: string = "";
  artists: Array<Artist> = [];
  selectedArtist: Artist | undefined;

  constructor(@Inject(ArtistService) private artistService: ArtistService, private router: Router) {
  }

  ngOnInit(): void {
    this.getArtists();
  }

  getArtists() {
    this.artistService.getArtists().subscribe({
      next: (artistResponse: ArtistResponse) => {
        this.artists = artistResponse.artists;
      }
    })
  }


  onDelete(artist: Artist) {
    this.artistService.deleteById(artist.id).subscribe({
      next: (artistResponse: ArtistResponse) => {
        this.artists = this.artists.filter(a => a.id !== artist.id);
      }
    });
  }


  onSelect(artist: Artist) {
    this.selectedArtist = artist;
  }


  goToDetail() {
    this.router.navigate(['/artist-details', this.selectedArtist?.id])
  }
}
