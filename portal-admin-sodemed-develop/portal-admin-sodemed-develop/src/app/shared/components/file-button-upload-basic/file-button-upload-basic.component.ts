import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FileUpload, FileUploadEvent, FileUploadModule } from 'primeng/fileupload';

@Component({
  selector: 'app-file-button-upload-basic',
  templateUrl: './file-button-upload-basic.component.html',
  styleUrls: ['./file-button-upload-basic.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FileUploadModule
  ]
})
export class FileButtonUploadBasicComponent implements OnInit {
  @ViewChild('fileUpload') primefileUpload: FileUpload;

  private plainFile: File;
  @Input() fileAccept: string = null;
  @Input() chooseLabel: string = 'Cargar archivo';
  @Input() maxFileSize: number | undefined = undefined;
  /**
   * @property disabled
   * @type boolean
   *  Si es true desabilita el funcionamiento del p-fileUpload
   */
  @Input() disabled: boolean = false;
  @Output() onEmittPlainFile: EventEmitter<any> = new EventEmitter();
  @Output() onEmittErrorUpload: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void { }

  getFileDestock() {
    if (this.primefileUpload && !this.disabled) {
      this.primefileUpload?.basicFileInput?.nativeElement.click();
    }
  }

  onErrorUpload(errorFile) {
    if (errorFile && !this.disabled) {
      this.onEmittErrorUpload.emit(errorFile)
    }
  }

  onBasicUploadAuto(file: FileUploadEvent) {
    if (file && !this.disabled) {
      this.primefileUpload.clear();
      this.plainFile = file.files[0];
      this.onEmittPlainFile.emit(this.plainFile)
    }
  }

  get getNamePlainFile() {
    const fileName = this.plainFile && this.plainFile?.name ? this.plainFile.name : '';
    return fileName
  }
}
