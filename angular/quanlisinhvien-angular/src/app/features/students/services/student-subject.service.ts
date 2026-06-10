import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { API_BASE_URL } from '../../../shared/config/api.config';
import { StudentSubject } from '../models/student-subject.model';

@Injectable({
  providedIn: 'root'
})
export class StudentSubjectService {
  private readonly apiUrl = `${API_BASE_URL}/student-subjects`;

  constructor(private readonly http: HttpClient) {}

  countRegisteredSubjects(studentId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/students/${studentId}/count`);
  }

  getScoresByStudent(studentId: string): Observable<StudentSubject[]> {
    return this.http.get<StudentSubject[]>(`${this.apiUrl}/students/${studentId}/scores`);
  }

  inputScore(
    studentId: string,
    subjectId: string,
    processScore: number,
    finalScore: number
  ): Observable<StudentSubject> {
    const params = new HttpParams()
      .set('studentId', studentId)
      .set('subjectId', subjectId)
      .set('processScore', processScore)
      .set('finalScore', finalScore);

    return this.http.post<StudentSubject>(`${this.apiUrl}/input-score`, null, { params });
  }

  getResult(studentId: string, subjectId: string): Observable<string> {
    const params = new HttpParams().set('studentId', studentId).set('subjectId', subjectId);

    return this.http.get(`${this.apiUrl}/result`, { params, responseType: 'text' });
  }
}
