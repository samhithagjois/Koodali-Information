import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionStudentOverview } from './section-student-overview';

describe('SectionStudentOverview', () => {
  let component: SectionStudentOverview;
  let fixture: ComponentFixture<SectionStudentOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SectionStudentOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SectionStudentOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
