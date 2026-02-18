import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMedicationRequestComponent } from './create-medication-request.component';

describe('CreateMedicationRequestComponent', () => {
  let component: CreateMedicationRequestComponent;
  let fixture: ComponentFixture<CreateMedicationRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMedicationRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMedicationRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
