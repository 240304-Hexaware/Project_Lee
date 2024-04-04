import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ErrorResponse, ParsedDataContainer } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class ParsedDataService {
  private baseUrl: string = `${environment.baseUrl}/records`;

  constructor(private http: HttpClient) {}

  getAllBySpec(specId: string): Observable<ParsedDataContainer[]> {
    return this.http
      .get<ParsedDataContainer[]>(`${this.baseUrl}?specId=${specId}`)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error(error);
          const errorResponse: ErrorResponse = {
            ...error.error,
          };
          return throwError(() => errorResponse);
        })
      );
  }

  fetchAllByParsedDataId(
    parsedDataId: string
  ): Observable<ParsedDataContainer[]> {
    return this.http
      .get<ParsedDataContainer[]>(
        `${this.baseUrl}?parsedDataId=${parsedDataId}`
      )
      .pipe(
        catchError((error: HttpErrorResponse) => {
          console.error(error);
          const errorResponse: ErrorResponse = {
            ...error.error,
          };
          return throwError(() => errorResponse);
        })
      );
  }

  fetchAll() {
    return this.http.get<ParsedDataContainer[]>(this.baseUrl).pipe(
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
