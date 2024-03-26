import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, throwError } from 'rxjs';
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

  private currentUserSubject = new BehaviorSubject<User | null>(null);

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
          this.currentUserSubject.next(user);
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

  register(params: RegisterRequestParams) {
    return this.http
      .post<RegisterResponse>(`${this.baseUrl}/users/register`, {
        params,
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

  getCurrentUser(): Observable<User | null> {
    return this.currentUserSubject.asObservable();
  }

  logout() {
    localStorage.removeItem(this.CURRENT_USER);
    this.currentUserSubject.next(null);
    this.http.post<void>(
      `${this.baseUrl}/users/logout`,
      {},
      { withCredentials: true }
    );
  }
}
