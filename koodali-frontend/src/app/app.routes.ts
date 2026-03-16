import {Routes} from '@angular/router';
import {AdminOverview} from './features/admin/admin-overview';
import {SectionOverview} from './features/admin/section-overview/section-overview';
import {UserForm} from './features/student/student-form/user-form';
import {StudentOverview} from './features/student/student-overview';
import {TeacherOverview} from './features/teacher/teacher-overview';
import {AdminTeacherOverview} from './features/admin/admin-teacher-overview/teacher-overview';
import {Leaderboard} from './features/leaderboard/leaderboard';
import {AdminStudentOverview} from './features/admin/admin-student-fees-overview/student-overview';
import {TeacherForm} from './features/teacher/teacher-form/teacher-form';
import {AddSectionForm} from './features/admin/section-overview/add-section-form/add-section-form';
import {
  SectionStudentOverview
} from './features/admin/section-overview/section-student-overview/section-student-overview';
import {
  SectionTeacherOverview
} from './features/admin/section-overview/section-teacher-overview/section-teacher-overview';
import {AttendanceOverview} from './features/teacher/attendance-overview/attendance-overview';
import {StudentProfile} from './features/student/student-profile/student-profile';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./features/home-view/home-view')
        .then(m => m.HomeView)
  },

  {
    path: 'admin', component: AdminOverview
  },
  {
    path: 'student', component: StudentOverview
  },
  {
    path: 'teacher', component: TeacherOverview
  },
  {
    path: 'leaderboard', component: Leaderboard
  },
  {
    path: 'student/edit-profile', component: UserForm
  },
  {
    path: 'student/see-profile/:id', component: StudentProfile
  },
  {
    path: 'admin/teacher-overview', component: AdminTeacherOverview
  },
  {
    path: 'admin/student-overview', component: AdminStudentOverview
  },
  {
    path: 'student/student-registration', component: UserForm
  },
  {
    path: 'teacher/edit-teacher-profile', component: TeacherForm
  },
  {
    path: 'teacher/attendance', component: AttendanceOverview
  },
  {
    path: 'teacher/attendance/:id', component: AttendanceOverview
  },
  {
    path: 'admin/sections', component: SectionOverview
  },
  {
    path: 'admin/sections/new', component: AddSectionForm
  },
  {
    path: 'admin/sections/:id/edit', component: AddSectionForm
  },
  {
    path: 'admin/sections/:id/students', component: SectionStudentOverview
  },
  {
    path: 'admin/sections/:id/teachers', component: SectionTeacherOverview
  },


];
