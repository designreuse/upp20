import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtResponse} from '../model/response/jwt-response';
import {BASE_API_URL} from '../shared/const';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  login(username: string, password: string): Observable<JwtResponse> {
    return this.httpClient.post<JwtResponse>(BASE_API_URL.concat('/auth/log-in'), {username, password});
  }
}
