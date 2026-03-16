import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherOverview } from './teacher-overview';

describe('TeacherOverview', () => {
  let component: TeacherOverview;
  let fixture: ComponentFixture<TeacherOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
