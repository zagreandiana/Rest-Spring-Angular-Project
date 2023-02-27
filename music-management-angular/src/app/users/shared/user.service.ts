import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "./userResponse.model";
import {User} from "./user.model";

@Injectable()
export class UserService {
  private usersUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<UserResponse> {
    return this.httpClient
      .get<UserResponse>(this.usersUrl);
  }

  deleteById(id: number) {
    const url = `${this.usersUrl}/${id}`;
    return this.httpClient.delete<UserResponse>(url);
  }

  save(user: User): Observable<UserResponse> {
    return this.httpClient.post<UserResponse>(this.usersUrl, user);
  }

  getUserById(id: number): Observable<UserResponse> {
    const url = `${this.usersUrl}/${id}`;
    return this.httpClient.get<UserResponse>(url);
  }

  update(user: User): Observable<UserResponse> {
    const url = `${this.usersUrl}/${user.id}`;
    return this.httpClient.put<UserResponse>(url, user);
  }
}

