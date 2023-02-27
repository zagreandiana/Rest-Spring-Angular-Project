import {Component, Inject, OnInit} from '@angular/core';
import {ArtistService} from "../shared/artist.service";
import {Location} from "@angular/common";
import {Artist} from "../shared/artist.model";
import {ArtistResponse} from "../shared/artistResponse.model";

@Component({
  selector: 'app-artist-new',
  templateUrl: './artist-new.component.html',
  styleUrls: ['./artist-new.component.css']
})
export class ArtistNewComponent implements  OnInit{

  constructor(@Inject(ArtistService) private artistService: ArtistService, private location: Location) {
  }

  ngOnInit(): void {
  }

  onSave(firstName: string, lastName: string, stageName: string, activityStartDate: string, activityEndDate: string) {
    const artistDto: Artist = {id: 0, firstName, lastName, stageName, activityStartDate, activityEndDate};

  this.artistService.save(artistDto).subscribe({
    next: (artistResponse: ArtistResponse) => {
      this.location.back();
    }})

  }

}
