import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';

import { AuthService } from '../../../core/auth/auth.service';
import { StudentSubject } from '../models/student-subject.model';
import { Student } from '../models/student.model';
import { Subject } from '../models/subject.model';
import { StudentSubjectService } from '../services/student-subject.service';
import { StudentService } from '../services/student.service';
import { SubjectService } from '../services/subject.service';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent implements OnInit {
  private readonly studentService = inject(StudentService);
  private readonly subjectService = inject(SubjectService);
  private readonly studentSubjectService = inject(StudentSubjectService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  students: Student[] = [];
  subjects: Subject[] = [];
  scores: StudentSubject[] = [];
  selectedStudent: Student | null = null;
  selectedSubject: Subject | null = null;
  selectedStudentId: string | null = null;
  subjectCount: number | null = null;
  resultMessage = '';
  message = '';
  loading = false;
  savingStudent = false;
  savingScore = false;

  formData: Student = this.createEmptyStudent();
  lookupStudentId = '';

  scoreForm = {
    studentId: '',
    subjectId: '',
    processScore: 0,
    finalScore: 0
  };

  readonly currentUser = this.authService.currentUser;

  get canEdit(): boolean {
    return this.currentUser()?.role === 'teacher';
  }

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.loading = true;
    this.message = '';

    forkJoin({
      students: this.studentService.getAll(),
      subjects: this.subjectService.getAll()
    }).subscribe({
      next: ({ students, subjects }) => {
        this.students = students;
        this.subjects = subjects;
        this.loading = false;

        if (!this.scoreForm.subjectId && subjects.length > 0) {
          this.scoreForm.subjectId = subjects[0].subjectId;
        }
      },
      error: () => {
        this.loading = false;
        this.message = 'Không thể tải dữ liệu ban đầu. Vui lòng kiểm tra backend và token đăng nhập.';
      }
    });
  }

  selectStudent(student: Student): void {
    this.selectedStudent = student;
    this.selectedStudentId = student.studentId;
    this.lookupStudentId = student.studentId;
    this.formData = { ...student };
    this.scoreForm.studentId = student.studentId;
    this.message = '';
    this.loadStudentLearningData(student.studentId);
  }

  findStudent(): void {
    const studentId = this.lookupStudentId.trim();

    if (!studentId) {
      this.message = 'Vui lòng nhập mã sinh viên cần tra cứu.';
      return;
    }

    this.studentService.getById(studentId).subscribe({
      next: (student) => {
        this.selectStudent(student);
        this.message = 'Đã tải chi tiết sinh viên.';
      },
      error: () => {
        this.message = 'Không tìm thấy sinh viên hoặc bạn không có quyền xem.';
      }
    });
  }

  selectSubject(subject: Subject): void {
    this.selectedSubject = subject;
    this.scoreForm.subjectId = subject.subjectId;
    this.reloadScoresForCurrentStudent();
    this.resultMessage = '';
  }

  onScoreSubjectChange(): void {
    this.selectedSubject =
      this.subjects.find((subject) => subject.subjectId === this.scoreForm.subjectId) ?? null;
    this.reloadScoresForCurrentStudent();
    this.resultMessage = '';
  }

  submitStudentForm(): void {
    if (!this.selectedStudentId) {
      this.message = 'Chọn một sinh viên trước khi cập nhật.';
      return;
    }

    if (!this.formData.fullName.trim()) {
      this.message = 'Vui lòng nhập họ tên sinh viên.';
      return;
    }

    this.savingStudent = true;

    this.studentService.update(this.selectedStudentId, this.formData).subscribe({
      next: (student) => {
        this.savingStudent = false;
        this.message = 'Cập nhật sinh viên thành công.';
        this.replaceStudent(student);
        this.selectStudent(student);
      },
      error: () => {
        this.savingStudent = false;
        this.message = 'Cập nhật thất bại. Tài khoản cần quyền teacher.';
      }
    });
  }

  submitScoreForm(): void {
    const { studentId, subjectId, processScore, finalScore } = this.scoreForm;

    if (!studentId || !subjectId) {
      this.message = 'Vui lòng chọn sinh viên và môn học trước khi nhập điểm.';
      return;
    }

    if (!this.isValidScore(processScore) || !this.isValidScore(finalScore)) {
      this.message = 'Điểm quá trình và điểm cuối kỳ phải nằm trong khoảng 0 đến 10.';
      return;
    }

    this.savingScore = true;

    this.studentSubjectService.inputScore(studentId, subjectId, processScore, finalScore).subscribe({
      next: () => {
        this.savingScore = false;
        this.message = 'Lưu điểm thành công.';
        this.loadStudentLearningData(studentId);
      },
      error: () => {
        this.savingScore = false;
        this.message = 'Lưu điểm thất bại. Tài khoản cần quyền teacher hoặc dữ liệu chưa hợp lệ.';
      }
    });
  }

  viewResult(): void {
    const { studentId, subjectId } = this.scoreForm;

    if (!studentId || !subjectId) {
      this.message = 'Vui lòng chọn sinh viên và môn học để xem kết quả.';
      return;
    }

    this.studentSubjectService.getResult(studentId, subjectId).subscribe({
      next: (result) => {
        this.resultMessage = result;
      },
      error: () => {
        this.resultMessage = 'Không thể xem kết quả. Sinh viên có thể chưa đăng ký môn học này.';
      }
    });
  }

  resetStudentForm(): void {
    this.selectedStudent = null;
    this.selectedStudentId = null;
    this.formData = this.createEmptyStudent();
    this.scores = [];
    this.subjectCount = null;
    this.resultMessage = '';
    this.lookupStudentId = '';
    this.scoreForm.studentId = '';
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  private loadStudentLearningData(studentId: string): void {
    forkJoin({
      count: this.studentSubjectService.countRegisteredSubjects(studentId),
      scores: this.studentSubjectService.getScoresByStudent(studentId)
    }).subscribe({
      next: ({ count, scores }) => {
        this.subjectCount = count;
        this.scores = scores;
        this.fillScoreFormFromSelectedSubject();
      },
      error: () => {
        this.subjectCount = null;
        this.scores = [];
        this.message = 'Không thể tải điểm hoặc số môn đăng ký của sinh viên.';
      }
    });
  }

  private reloadScoresForCurrentStudent(): void {
    if (!this.scoreForm.studentId) {
      this.message = 'Vui lòng chọn sinh viên trước khi chọn môn học.';
      return;
    }

    this.studentSubjectService.getScoresByStudent(this.scoreForm.studentId).subscribe({
      next: (scores) => {
        this.scores = scores;
        this.fillScoreFormFromSelectedSubject();
      },
      error: () => {
        this.scores = [];
        this.scoreForm.processScore = 0;
        this.scoreForm.finalScore = 0;
        this.message = 'Không thể tải lại điểm của sinh viên.';
      }
    });
  }

  private replaceStudent(updatedStudent: Student): void {
    this.students = this.students.map((student) =>
      student.studentId === updatedStudent.studentId ? updatedStudent : student
    );
  }

  private fillScoreFormFromSelectedSubject(): void {
    if (!this.scoreForm.studentId || !this.scoreForm.subjectId) {
      return;
    }

    const currentScore = this.scores.find(
      (item) => item.subject.subjectId === this.scoreForm.subjectId
    );

    this.scoreForm.processScore = currentScore?.processScore ?? 0;
    this.scoreForm.finalScore = currentScore?.finalScore ?? 0;
  }

  private isValidScore(score: number): boolean {
    return Number.isFinite(score) && score >= 0 && score <= 10;
  }

  private createEmptyStudent(): Student {
    return {
      studentId: '',
      fullName: '',
      gender: 'Nam',
      dateOfBirth: '',
      className: '',
      course: ''
    };
  }
}
