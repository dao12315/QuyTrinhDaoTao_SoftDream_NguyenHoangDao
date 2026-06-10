import { Student } from './student.model';
import { Subject } from './subject.model';

export interface StudentSubject {
  id: number;
  processScore: number | null;
  finalScore: number | null;
  student: Student;
  subject: Subject;
  totalScore: number | null;
  passed: boolean | null;
}
