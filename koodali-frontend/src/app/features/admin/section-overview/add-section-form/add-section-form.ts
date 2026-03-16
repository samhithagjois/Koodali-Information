import {Component, inject, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';



@Component({
  selector: 'app-add-section-form',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './add-section-form.html',
  styleUrl: './add-section-form.css',
})
export class AddSectionForm implements OnInit {

  section = {
    name: '',
    linkOrAddress: ''
  }

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  isEditMode = false;
  sectionId?: string | null;


  ngOnInit() {


    this.sectionId = this.route.snapshot.paramMap.get('id');

    if (this.sectionId) {
      this.isEditMode = true;
      this.loadSection(this.sectionId);
    }
  }


  constructor(private http: HttpClient) {
  }

  onSubmit() {
    if (this.isEditMode) {
      this.updateSection();
    } else {
      this.createSection();
    }
  }

  createSection() {
    this.http.post(`http://localhost:8080/api/sections`, this.section)
      .subscribe(() => {
        {
          this.router.navigateByUrl('/admin/sections', { skipLocationChange: true })
            .then(() => {
              this.router.navigate(['/admin/sections']);
            });

        }
      });
  }

  updateSection() {
    this.http.put(`http://localhost:8080/api/sections/${this.sectionId}`, this.section)
      .subscribe(() => {
        {
          this.router.navigateByUrl('/admin/sections', { skipLocationChange: true })
            .then(() => {
              this.router.navigate(['/admin/sections']);
            });
        }
      });
  }

  loadSection(id: string) {
    this.http
      .get<any>(`http://localhost:8080/api/sections/${id}`)
      .subscribe(section => {
        this.section = section;
      });
  }
}
