import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStudentOverview } from './student-overview';

describe('AdminStudentOverview', () => {
  let component: AdminStudentOverview;
  let fixture: ComponentFixture<AdminStudentOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminStudentOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminStudentOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
