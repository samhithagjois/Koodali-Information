import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-student-overview',
  standalone : true,
  imports: [
    RouterLink
  ],
  templateUrl: './student-overview.html',
  styleUrl: './student-overview.css',
})
export class StudentOverview implements OnInit{


  students: any[] = [];



  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.http.get<any[]>('http://localhost:8080/api/students')
      .subscribe(data => {
        this.students = data;
      });
  }

  calculateAttendanceForStudent(id: string,section:string) {
    this.http.get<any[]>(`http://localhost:8080/api/teachers/attendance/${section}/${id}`)
      .subscribe(data => {
        return data;
      });
  }





}
