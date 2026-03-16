import {Component, OnInit} from '@angular/core';
import {NavigationEnd, RouterLink, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-section-overview',
  standalone : true,
  imports: [
    RouterLink
  ],
  template: `

    <button routerLink=".."> Back</button>
    <h3>Section Overview for Administration</h3>

    <div>
      <table class="tg"><thead>
      <tr>
        <th >Section Name</th>
        <th>Section Link or Address</th>
        <th >Teacher(s)</th>
        <th >Students</th>
        <th >Schedule</th>
        <th > </th>
      </tr></thead>
        <tbody>
        @for (section of sections; track section.id){
        <tr>
          <td> {{section.name }}</td>
        <td>{{section.linkOrAddress}} </td>
        <td>
        <a [routerLink]="['/admin/sections',section.id,'teachers']" routerLinkActive="$router_link-active$" >
        Teachers
        </a>
        </td>
        <td> <a [routerLink]="['/admin/sections',section.id,'students']" routerLinkActive="$router_link-active$" > Students </a> </td>
        <td> </td>
        <td> <button [routerLink]="['/admin/sections',section.id,'edit']">Edit section</button> </td>
      </tr>
        }
        </tbody>
      </table>
    </div>
    <button routerLink="."> Display</button>
    <button routerLink="new"> Add a section</button>

    <button type="button"> Export to Excel</button>
  `,
  styleUrl: './section-overview.css',
})
export class SectionOverview implements OnInit {

  sections: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {

    this.loadSections();

  }


  loadSections() {
    this.http.get<any[]>(`http://localhost:8080/api/sections`)
      .subscribe(data => {
        this.sections = data;
      });
  }

}
