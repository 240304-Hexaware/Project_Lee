import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, throwError } from 'rxjs';
import {
  ErrorResponse,
  LoginRequestParams,
  RegisterRequestParams,
  RegisterResponse,
  User,
} from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080';

  private CURRENT_USER: string = 'currentUser';

  private currentUserSubject = new BehaviorSubject<User | null>(
    localStorage.getItem(this.CURRENT_USER) as unknown as User
  );

  constructor(private http: HttpClient) {}

  login(params: LoginRequestParams) {
    return this.http
      .post<User>(`${this.baseUrl}/users/login`, params, {
        withCredentials: true,
      })
      .pipe(
        map((user) => {
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
      .post<RegisterResponse>(`${this.baseUrl}/users/register`, params)
      .pipe(
        catchError((error) => {
          const errorResponse: ErrorResponse = {
            ...error.error,
          };
          return throwError(() => errorResponse);
        })
      );
  }

  getCurrentUser(): Observable<User | null> {
    return this.currentUserSubject.asObservable();
  }

  logout() {
    return this.http
      .post<void>(`${this.baseUrl}/users/logout`, {}, { withCredentials: true })
      .pipe(
        map(() => {
          localStorage.removeItem(this.CURRENT_USER);
          this.currentUserSubject.next(null);
        })
      );
  }
}
