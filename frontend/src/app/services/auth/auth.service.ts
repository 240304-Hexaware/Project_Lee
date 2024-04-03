import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
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
  baseUrl: string = `${environment.baseUrl}/users`;

  private CURRENT_USER: string = 'currentUser';

  private currentUserSubject = new BehaviorSubject<User | null>(
    localStorage.getItem(this.CURRENT_USER) as unknown as User
  );

  constructor(private http: HttpClient) {}

  login(params: LoginRequestParams) {
    return this.http.post<User>(`${this.baseUrl}/login`, params).pipe(
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
      .post<RegisterResponse>(`${this.baseUrl}/register`, params)
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
    return this.http.post<void>(`${this.baseUrl}/logout`, {}).pipe(
      map(() => {
        localStorage.removeItem(this.CURRENT_USER);
        this.currentUserSubject.next(null);
      })
    );
  }
}
