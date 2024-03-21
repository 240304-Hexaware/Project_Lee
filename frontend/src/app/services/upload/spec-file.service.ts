import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { ErrorResponse, Specification } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class SpecFileService {
  private baseUrl: string = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  uploadSpec(file: File) {
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http
      .post<Specification>(`${this.baseUrl}/specs`, formData, {
        withCredentials: true,
      })
      .pipe(
        map((data) => {
          return data;
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

  getAllSpecs() {
    return this.http
      .get<Specification[]>(`${this.baseUrl}/specs`, {
        withCredentials: true,
      })
      .pipe(
        map((data) => {
          return data;
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
}
