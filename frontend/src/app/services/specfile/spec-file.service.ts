import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ErrorResponse, Specification } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class SpecFileService {
  private baseUrl: string = `${environment.baseUrl}/specs`;
  constructor(private http: HttpClient) {}

  uploadSpec(file: File, name: string) {
    const formData = new FormData();
    formData.append('file', file, file.name);
    formData.append('name', name);
    return this.http.post<Specification>(this.baseUrl, formData).pipe(
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
    return this.http.get<Specification[]>(this.baseUrl).pipe(
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
