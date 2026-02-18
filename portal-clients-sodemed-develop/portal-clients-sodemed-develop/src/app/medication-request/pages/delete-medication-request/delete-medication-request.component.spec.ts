import { ComponentFixture, TestBed } from '@angular/core/testing';
import DeleteMedicationRequestComponent from './delete-medication-request.component';


describe('DeleteMedicationRequestComponent', () => {
  let component: DeleteMedicationRequestComponent;
  let fixture: ComponentFixture<DeleteMedicationRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteMedicationRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteMedicationRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
