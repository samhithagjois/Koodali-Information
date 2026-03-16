import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-teacher-form',
  standalone : true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './teacher-form.html',
  styleUrl: './teacher-form.css',
})
export class TeacherForm {
  //code taken from https://engineerscodinghub.com/form-handling-in-angular-and-spring-boot/

  teacher = {
    firstName: '',
    lastName: '',
    city: '',
    pinCode: '',
    fullPostalAddress: '',
    country: '',
    dateOfBirth: '',
    emailID: '',
    phoneNumber: '',
    whatsappNumber: '',
  };

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/teachers', this.teacher)
      .subscribe(() => {
        alert('Form submitted successfully!');
      });
  }
}
