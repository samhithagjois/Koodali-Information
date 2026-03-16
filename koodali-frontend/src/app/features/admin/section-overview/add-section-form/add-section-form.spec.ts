import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSectionForm } from './add-section-form';

describe('AddSectionForm', () => {
  let component: AddSectionForm;
  let fixture: ComponentFixture<AddSectionForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSectionForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSectionForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
