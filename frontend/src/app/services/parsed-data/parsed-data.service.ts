import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ParsedDataContainer } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class ParsedDataService {
  private baseUrl: string = `${environment.baseUrl}/records`;

  constructor(private http: HttpClient) {}

  getAllBySpec(specId: string): Observable<ParsedDataContainer[]> {
    // TODO: error handler
    return this.http.get<ParsedDataContainer[]>(
      `${this.baseUrl}?specId=${specId}`,
      {
        withCredentials: true,
      }
    );
  }

  fetchAllByParsedDataId(
    parsedDataId: string
  ): Observable<ParsedDataContainer[]> {
    return this.http.get<ParsedDataContainer[]>(
      `${this.baseUrl}?parsedDataId=${parsedDataId}`,
      {
        withCredentials: true,
      }
    );
  }

  fetchAll() {
    return this.http.get<ParsedDataContainer[]>(this.baseUrl, {
      withCredentials: true,
    });
  }
}
