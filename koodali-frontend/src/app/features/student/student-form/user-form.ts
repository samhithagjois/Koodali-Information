import {Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './user-form.html'
})
export class UserForm implements OnInit{
  //code taken from https://engineerscodinghub.com/form-handling-in-angular-and-spring-boot/

  user = {
    firstName: '',
    lastName: '',
    sectionName: '',
    city: '',
    pinCode: '',
    fullPostalAddress: '',
    country: '',
    dateOfBirth: '',
    mothersName: '',
    fathersName: '',
    fathersEmailID: '',
    mothersEmailID: '',
    childEmailID: '',
    phoneNumber: '',
    whatsappNumber: '',
    pathToPhoto:'',
    pathToConsentForm:''
  };
  sections : any[] = []

  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.loadSections();
  }
  loadSections() {
    this.http.get<any[]>('http://localhost:8080/api/sections')
      .subscribe(data => {
        this.sections = data;
      });
  }

  onSubmit() {
    console.log("Submitted : ",this.user);
    this.http.post('http://localhost:8080/api/students', this.user)
      .subscribe(() => {
        alert('Form submitted successfully!');


      });
  }

  onPathToPhotoFileSelected(event : any) {

    const file:File = event.target.files[0];

    if (file) {

      this.user.pathToPhoto = file.name;

      const formData = new FormData();

      formData.append("thumbnail", file);

    }
  }

  onPathToConsentFormFileSelected(event : any) {

    const file:File = event.target.files[0];
    //TODO:fix these methods

    if (file) {

      this.user.pathToConsentForm = file.name;

      const formData = new FormData();

      formData.append("thumbnail", file);

    }
  }
}
