import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileButtonUploadBasicComponent } from './file-button-upload-basic.component';

describe('FileButtonUploadBasicComponent', () => {
  let component: FileButtonUploadBasicComponent;
  let fixture: ComponentFixture<FileButtonUploadBasicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FileButtonUploadBasicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FileButtonUploadBasicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
