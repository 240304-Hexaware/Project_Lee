import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ErrorResponse, Metadata } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class MetadataService {
  private baseUrl: string = `${environment.baseUrl}/metadata`;

  constructor(private http: HttpClient) {}

  getMetadata(): Observable<Metadata[]> {
    return this.http.get<Metadata[]>(this.baseUrl).pipe(
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
