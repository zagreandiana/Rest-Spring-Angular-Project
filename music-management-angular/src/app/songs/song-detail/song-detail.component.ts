import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs";
import {Location} from "@angular/common";
import {Song} from '../shared/song.model';
import {SongService} from "../shared/song.service";
import {SongDtos} from "../shared/songDtos.model";

@Component({
  selector: 'app-song-detail',
  templateUrl: './song-detail.component.html',
  styleUrls: ['./song-detail.component.css']
})
export class SongDetailComponent implements OnInit {
  @Input() song!: Song;

  constructor(private route: ActivatedRoute, private songService: SongService, private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.songService.getSongId(+params['id'])))
      .subscribe({
        next: (songDtos: SongDtos) => {
          this.song = songDtos.songDtoSet.at(0) ?? <Song>{}
        }
      });
  }

  goBack() {
    this.location.back();
  }

  save() {
    this.songService.update(this.song).subscribe({
      next: (songDtos: SongDtos) => {
        this.goBack();
      }
    })
  }
}
