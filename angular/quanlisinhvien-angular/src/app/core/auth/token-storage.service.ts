import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

import { CurrentUser } from './auth.models';

const TOKEN_KEY = 'qlsv_token';
const USER_KEY = 'qlsv_user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private readonly isBrowser: boolean;

  constructor(@Inject(PLATFORM_ID) platformId: object) {
    this.isBrowser = isPlatformBrowser(platformId);
  }

  getToken(): string | null {
    if (!this.isBrowser) {
      return null;
    }

    return localStorage.getItem(TOKEN_KEY);
  }

  saveToken(token: string): void {
    if (!this.isBrowser) {
      return;
    }

    localStorage.setItem(TOKEN_KEY, token);
  }

  getUser(): CurrentUser | null {
    if (!this.isBrowser) {
      return null;
    }

    const rawUser = localStorage.getItem(USER_KEY);

    if (!rawUser) {
      return null;
    }

    try {
      return JSON.parse(rawUser) as CurrentUser;
    } catch {
      this.clear();
      return null;
    }
  }

  saveUser(user: CurrentUser): void {
    if (!this.isBrowser) {
      return;
    }

    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  clear(): void {
    if (!this.isBrowser) {
      return;
    }

    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
  }
}
