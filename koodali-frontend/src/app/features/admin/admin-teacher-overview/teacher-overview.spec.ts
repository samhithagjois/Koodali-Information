import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTeacherOverview } from './teacher-overview';

describe('TeacherOverview', () => {
  let component: AdminTeacherOverview;
  let fixture: ComponentFixture<AdminTeacherOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTeacherOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTeacherOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
