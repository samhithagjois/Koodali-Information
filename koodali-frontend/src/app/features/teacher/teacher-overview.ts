import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-teacher-overview',
  standalone : true,
  imports: [
    RouterLink
  ],
  templateUrl: './teacher-overview.html',
  styleUrl: './teacher-overview.css',
})
export class TeacherOverview {

}
