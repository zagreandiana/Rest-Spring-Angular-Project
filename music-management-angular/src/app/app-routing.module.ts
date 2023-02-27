import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {UserNewComponent} from "./users/user-new/user-new.component";
import {UserDetailComponent} from "./users/user-detail/user-detail.component";
import {SongsComponent} from "./songs/songs.component";
import {SongNewComponent} from "./songs/song-new/song-new.component";
import {SongDetailComponent} from "./songs/song-detail/song-detail.component";
import {ArtistsComponent} from "./artists/artists.component";
import {ArtistNewComponent} from "./artists/artist-new/artist-new.component";
import {ArtistDetailsComponent} from "./artists/artist-details/artist-details.component";

const routes: Routes = [
  {path: 'users', component: UsersComponent},
  {path: 'user-new', component: UserNewComponent},
  {path: 'user-detail/:id', component: UserDetailComponent},
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'songs',component:SongsComponent},
  {path: 'song-new', component: SongNewComponent},
  {path: 'song-detail/:id',component:SongDetailComponent},

  {path: 'artists', component: ArtistsComponent},
  {path: 'artist-new', component: ArtistNewComponent},
  {path: 'artist-details/:id', component: ArtistDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
