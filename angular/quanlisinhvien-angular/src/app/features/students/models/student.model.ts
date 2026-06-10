export interface Student {
  studentId: string;
  fullName: string;
  gender: string;
  dateOfBirth: string;
  className: string;
  course: string;
  user?: User;
}

export interface User {
  id: number;
  email: string;
  roles: string;
  createdAt: string;
}
