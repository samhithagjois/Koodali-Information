import {Component, inject, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-student-profile',
  imports: [],
  standalone : true,
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css',
})
export class StudentProfile implements OnInit{

  private route = inject(ActivatedRoute);


  student: any;
  studentId: any;

  ngOnInit(): void {
    this.studentId = this.route.snapshot.paramMap.get('id');

    this.loadStudent(this.studentId);
  }

  constructor(private http: HttpClient) {

  }

  // @GetMapping("/students/{id}")

  loadStudent(id: string) {
    this.http
      .get<any>(`http://localhost:8080/api/students/${id}`)
      .subscribe(student => {
        this.student = student;
      });
  }




}
