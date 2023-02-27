import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {UsersComponent} from './users/users.component';
import {UserService} from "./users/shared/user.service";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import { UserListComponent } from './users/user-list/user-list.component';
import { UserNewComponent } from './users/user-new/user-new.component';
import { UserDetailComponent } from './users/user-detail/user-detail.component';
import {FormsModule} from "@angular/forms";
import {SongsComponent} from "./songs/songs.component";
import {SongsListComponent} from "./songs/songs-list/songs-list.component";
import {SongService} from "./songs/shared/song.service";
import { SongNewComponent } from './songs/song-new/song-new.component';
import { SongDetailComponent } from './songs/song-detail/song-detail.component';

import { ArtistsComponent } from './artists/artists.component';
import {ArtistService} from "./artists/shared/artist.service";
import { ArtistsListComponent } from './artists/artists-list/artists-list.component';
import { ArtistNewComponent } from './artists/artist-new/artist-new.component';
import { ArtistDetailsComponent } from './artists/artist-details/artist-details.component';


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserListComponent,
    UserNewComponent,
    UserDetailComponent,
    SongsComponent,
    SongsListComponent,
    SongNewComponent,
    SongDetailComponent,
    ArtistsComponent,
    ArtistsListComponent,
    ArtistNewComponent,
    ArtistDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [UserService, SongService, ArtistService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
