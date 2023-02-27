import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SongDtos} from "./songDtos.model";
import {Song} from "./song.model";


@Injectable()
export class SongService {
  private songsUrl = 'http://localhost:8080/api/songs';

  constructor(private httpClient: HttpClient) {
  }

  getSongs(): Observable<SongDtos> {
    return this.httpClient
      .get<SongDtos>(this.songsUrl);
  }

  deleteById(id: number) {
    const url = `${this.songsUrl}/${id}`;
    return this.httpClient.delete<SongDtos>(url);
  }

  save(song: Song):Observable<SongDtos> {
    return this.httpClient.post<SongDtos>(this.songsUrl, song);
  }

  getSongId(id: number): Observable<SongDtos> {
    const url = `${this.songsUrl}/${id}`;
    return this.httpClient.get<SongDtos>(url);
  }

  update(song: Song): Observable<SongDtos> {
    const url = `${this.songsUrl}/${song.id}`;
    return this.httpClient.put<SongDtos>(url, song);
  }
}
