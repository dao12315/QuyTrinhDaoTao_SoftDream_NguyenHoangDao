import { Injectable, computed, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { API_BASE_URL } from '../../shared/config/api.config';
import { CurrentUser, LoginRequest, LoginResponse, RegisterRequest } from './auth.models';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly tokenStorage = inject(TokenStorageService);
  private readonly apiUrl = `${API_BASE_URL}/auth`;
  private readonly userSignal = signal<CurrentUser | null>(this.tokenStorage.getUser());

  readonly currentUser = this.userSignal.asReadonly();
  readonly isAuthenticated = computed(() => !!this.tokenStorage.getToken());

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap((response) => {
        this.applySession(response);
      })
    );
  }

  register(request: RegisterRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/register`, request).pipe(
      tap((response) => {
        this.applySession(response);
      })
    );
  }

  me(): Observable<unknown> {
    return this.http.get(`${this.apiUrl}/me`);
  }

  logout(): void {
    this.tokenStorage.clear();
    this.userSignal.set(null);
  }

  private applySession(response: LoginResponse): void {
    this.tokenStorage.saveToken(response.token);
    const user: CurrentUser = {
      userId: response.userId,
      email: response.email,
      role: response.role
    };
    this.tokenStorage.saveUser(user);
    this.userSignal.set(user);
  }
}
