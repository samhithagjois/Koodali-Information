import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-section-teacher-overview',
  standalone:true,
  imports: [
    RouterLink
  ],
  templateUrl: './section-teacher-overview.html',
  styleUrl: './section-teacher-overview.css',
})
export class SectionTeacherOverview implements OnInit{
  classId!:string;
  teachers : any[] = [];
  constructor(private http: HttpClient,
              private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.classId = this.route.snapshot.paramMap.get('id')!;
    this.loadTeachers();

  }

  loadTeachers(){
    if(this.classId == null){
      return;
    }
    this.http.get<any[]>(`http://localhost:8080/api/admin/sections/${this.classId}/teachers`)
      .subscribe(data => {
        this.teachers = data;
      });
  }

}

