import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Metadata } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class MetadataService {
  private baseUrl: string = 'http://localhost:8080/metadata';

  constructor(private http: HttpClient) { }

  getMetadata(): Observable<Metadata[]> {
    return this.http.get<Metadata[]>(this.baseUrl, { withCredentials: true });
  }
}
