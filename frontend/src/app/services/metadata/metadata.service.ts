import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { Metadata } from '../../utils/types';

@Injectable({
  providedIn: 'root',
})
export class MetadataService {
  private baseUrl: string = `${environment.baseUrl}/metadata`;

  constructor(private http: HttpClient) {}

  getMetadata(): Observable<Metadata[]> {
    return this.http.get<Metadata[]>(this.baseUrl);
  }
}
