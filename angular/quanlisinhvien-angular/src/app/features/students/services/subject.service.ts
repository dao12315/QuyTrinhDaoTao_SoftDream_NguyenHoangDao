import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { API_BASE_URL } from '../../../shared/config/api.config';
import { Subject } from '../models/subject.model';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private readonly apiUrl = `${API_BASE_URL}/subjects`;

  constructor(private readonly http: HttpClient) {}

  getAll(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.apiUrl);
  }

  getById(subjectId: string): Observable<Subject> {
    return this.http.get<Subject>(`${this.apiUrl}/${subjectId}`);
  }
}
