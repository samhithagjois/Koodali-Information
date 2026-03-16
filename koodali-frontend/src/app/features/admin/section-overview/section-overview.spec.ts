import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionOverview } from './section-overview';

describe('SectionOverview', () => {
  let component: SectionOverview;
  let fixture: ComponentFixture<SectionOverview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SectionOverview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SectionOverview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
