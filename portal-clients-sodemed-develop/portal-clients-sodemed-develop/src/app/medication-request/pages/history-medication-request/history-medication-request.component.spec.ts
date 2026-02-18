import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryMedicationRequestComponent } from './history-medication-request.component';

describe('HistoryMedicationRequestComponent', () => {
  let component: HistoryMedicationRequestComponent;
  let fixture: ComponentFixture<HistoryMedicationRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryMedicationRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryMedicationRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
