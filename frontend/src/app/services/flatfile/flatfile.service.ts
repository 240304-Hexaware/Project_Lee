import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ErrorResponse, FlatFile } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class FlatFileService {
  private baseUrl: string = `${environment.baseUrl}/files`;
  constructor(private http: HttpClient) {}

  uploadFlatFile(file: File) {
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http
      .post<FlatFile>(this.baseUrl, formData, {
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

  getAllFlatFile() {
    return this.http
      .get<FlatFile[]>(this.baseUrl, {
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
