import {Component, OnInit} from '@angular/core';
import {Song} from "../shared/song.model";
import {SongService} from "../shared/song.service";
import {SongDtos} from "../shared/songDtos.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-songs-list',
  templateUrl: './songs-list.component.html',
  styleUrls: ['./songs-list.component.css']
})
export class SongsListComponent implements OnInit {
  songs: Array<Song> = [];
  selectedSong: Song | undefined

  constructor(private songService: SongService, private router: Router) {
  }

  ngOnInit(): void {
    this.getSongs();
  }

  getSongs() {
    this.songService.getSongs().subscribe({
      next: (songResponse: SongDtos) => {
        this.songs = songResponse.songDtoSet;
        /* },
         error: (err: any) => {
           this.errorMessage = err;*/
      }
    })
  }

  onDelete(song: Song) {
    this.songService.deleteById(song.id).subscribe({
      next: (songDto: SongDtos) => {
        this.songs = this.songs.filter(s => s.id !== song.id);
        /*},
        error: (err: any) => {
          this.errorMessage = err;*/
      }
    });
  }

  onSelect(song: Song) {
    this.selectedSong = song;
  }

  gotoDetail() {
    this.router.navigate(['/song-detail', this.selectedSong?.id]);
  }
}



