import { TestBed } from '@angular/core/testing';

import { MedicalRequestService } from './medical-request.service';

describe('MedicalRequestService', () => {
  let service: MedicalRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
