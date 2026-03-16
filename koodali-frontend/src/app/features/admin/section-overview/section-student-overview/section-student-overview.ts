import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-section-student-overview',
  standalone:true,
  imports: [
    RouterLink
  ],
  templateUrl: './section-student-overview.html',
  styleUrl: './section-student-overview.css',
})
export class SectionStudentOverview implements OnInit{
  classId!:string;


  student : any = {
    personID :'',
    firstName :'',
    lastName :'',
    homeworkPoints:'',
    attendancePercentage:''
  }
  students : any[] = [];
  constructor(private http: HttpClient,
              private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.classId = this.route.snapshot.paramMap.get('id')!;
    this.loadStudents();

  }

  loadStudents(){
    if(this.classId == null){
      return;
    }
    this.http.get<any[]>(`http://localhost:8080/api/admin/sections/${this.classId}/students`)
      .subscribe(data => {
        this.students = data;
      });
  }

}
