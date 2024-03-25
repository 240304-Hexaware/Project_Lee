import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { ParsedData, ParsingRequestParams } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class ParsingService {
  private baseUrl: string = 'http://localhost:8080/tasks';

  constructor(private http: HttpClient) {}

  parse(params: ParsingRequestParams): Observable<ParsedData> {
    return this.http
      .post<ParsedData>(this.baseUrl, params, {
        withCredentials: true,
      })
      .pipe(
        map((data) => data),
        catchError((error) => {
          console.error(error);
          return of(error);
        })
      );
  }
}
