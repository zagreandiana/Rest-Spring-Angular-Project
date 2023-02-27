import {Component, OnInit} from '@angular/core';
import {SongService} from "../shared/song.service";
import {Location} from "@angular/common";
import {Song} from "../shared/song.model";
import {SongDtos} from "../shared/songDtos.model";

@Component({
  selector: 'app-song-new',
  templateUrl: './song-new.component.html',
  styleUrls: ['./song-new.component.css']
})
export class SongNewComponent implements OnInit {
  errorMessage: string = "";

  constructor(private songService: SongService, private location: Location) {
  }

  ngOnInit(): void {
  }

  onSave(title: string, albumId: any, time: string) {
    const songDto: Song = {id: 0, title,albumId, time};

    this.songService.save(songDto).subscribe({
      next: (songDtos: SongDtos) => {
        this.location.back();
      },
      error: (err: any) => {
        this.errorMessage = err;
      }
    })
  }
}
