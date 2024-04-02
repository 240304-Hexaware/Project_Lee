import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import {
  ErrorResponse,
  ParsedDataContainer,
  ParsingRequestParams,
} from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class ParsingService {
  private baseUrl: string = `${environment.baseUrl}/tasks`;

  constructor(private http: HttpClient) {}

  parse(params: ParsingRequestParams): Observable<ParsedDataContainer> {
    return this.http
      .post<ParsedDataContainer>(this.baseUrl, params, {
        withCredentials: true,
      })
      .pipe(
        catchError((error) => {
          const errorResponse: ErrorResponse = {
            ...error.error,
          };
          return throwError(() => errorResponse);
        })
      );
  }
}
