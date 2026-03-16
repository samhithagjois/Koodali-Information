import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionTeacherOverview } from './section-teacher-overview';

describe('SectionTeacherOverview', () => {
  let component: SectionTeacherOverview;
  let fixture: ComponentFixture<SectionTeacherOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SectionTeacherOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SectionTeacherOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
