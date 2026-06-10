import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../../core/auth/auth.service';
import { LoginRequest, RegisterRequest } from '../../../core/auth/auth.models';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  mode: 'login' | 'register' = 'login';

  formData: LoginRequest = {
    email: '',
    password: ''
  };

  registerData: RegisterRequest = {
    email: '',
    password: '',
    role: 'student'
  };

  loading = false;
  message = '';

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router
  ) {}

  submit(): void {
    this.message = '';

    if (!this.formData.email || !this.formData.password) {
      this.message = 'Vui lòng nhập email và mật khẩu.';
      return;
    }

    this.loading = true;

    this.authService.login(this.formData).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/students']);
      },
      error: () => {
        this.loading = false;
        this.message = 'Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản.';
      }
    });
  }

  register(): void {
    this.message = '';

    if (!this.registerData.email || !this.registerData.password || !this.registerData.role) {
      this.message = 'Vui lòng nhập đầy đủ email, mật khẩu và vai trò.';
      return;
    }

    this.loading = true;

    this.authService.register(this.registerData).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/students']);
      },
      error: () => {
        this.loading = false;
        this.message = 'Đăng ký thất bại. Email có thể đã tồn tại hoặc dữ liệu chưa hợp lệ.';
      }
    });
  }

  setMode(mode: 'login' | 'register'): void {
    this.mode = mode;
    this.message = '';
  }
}
