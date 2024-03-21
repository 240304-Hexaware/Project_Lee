import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import {
  ErrorResponse,
  RegisterRequestParams,
  RegisterResponse,
  User,
} from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080';

  isLoggedIn: boolean = false;

  private CURRENT_USER: string = 'currentUser';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http
      .post<User>(
        `${this.baseUrl}/users/login`,
        {
          username,
          password,
        },
        {
          withCredentials: true,
        }
      )
      .pipe(
        map((user) => {
          console.log('user', user);
          localStorage.setItem(this.CURRENT_USER, JSON.stringify(user));
          this.isLoggedIn = true;
          return user;
        }),
        catchError((error: HttpErrorResponse) => {
          console.error(error);
          const errorResponse: ErrorResponse = {
            ...error.error,
          };
          return throwError(() => errorResponse);
        })
      );
  }

  register({ username, password, confirmPassword }: RegisterRequestParams) {
    return this.http
      .post<RegisterResponse>(`${this.baseUrl}/users/register`, {
        username,
        password,
        confirmPassword,
      })
      .pipe(
        map((response) => {
          console.log('response', response);
        }),
        catchError((error) => {
          console.error(error);
          throw new Error(error);
        })
      );
  }

  getCurrentUserUsername(): string {
    const currentUser = localStorage.getItem(this.CURRENT_USER);
    if (currentUser == null) {
      return '';
    }
    return (JSON.parse(currentUser) as User).username;
  }

  logout() {
    localStorage.removeItem(this.CURRENT_USER);
    // TODO: send something to server to invalidate token
    this.isLoggedIn = false;
  }
}
