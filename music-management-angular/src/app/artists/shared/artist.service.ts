import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ArtistResponse} from "./artistResponse.model";
import {Artist} from "./artist.model";

@Injectable()
export class ArtistService {
  private artistsUrl = 'http://localhost:8080/api/artists';

  constructor(private httpClient: HttpClient) {
  }

  getArtists(): Observable<ArtistResponse> {
    return this.httpClient
      .get<ArtistResponse>(this.artistsUrl);
  }

  save(artist: Artist): Observable<ArtistResponse> {
    return this.httpClient.post<ArtistResponse>(this.artistsUrl, artist);
  }

  getArtistById(id: number): Observable<ArtistResponse> {
    const url = `${this.artistsUrl}/${id}`;
    return this.httpClient.get<ArtistResponse>(url);
  }

  update(artist: Artist): Observable<ArtistResponse> {
    const url = `${this.artistsUrl}/${artist.id}`;
    return this.httpClient.put<ArtistResponse>(url, artist);
  }

  deleteById(id: number) {
    const url = `${this.artistsUrl}/${id}`;
    return this.httpClient.delete<ArtistResponse>(url);
  }
}
