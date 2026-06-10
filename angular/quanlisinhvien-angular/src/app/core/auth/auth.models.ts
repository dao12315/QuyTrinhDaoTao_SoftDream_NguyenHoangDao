export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest extends LoginRequest {
  role: 'student' | 'teacher' | string;
}

export interface LoginResponse {
  userId: number;
  email: string;
  role: 'student' | 'teacher' | string;
  token: string;
  message: string;
}

export type CurrentUser = Pick<LoginResponse, 'userId' | 'email' | 'role'>;
