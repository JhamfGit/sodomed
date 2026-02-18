import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
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

  constructor(
    private notificationService: NotificationService
  ) { }

  ngOnInit(): void { }

  getFileDestock() {
    if (this.primefileUpload && !this.disabled) {
      this.primefileUpload?.basicFileInput?.nativeElement.click();
    }
  }

  get getNamePlainFile() {
    const fileName =
      this.plainFile && this.plainFile?.name ? this.plainFile.name : '';
    return fileName;
  }
  onSelect(file) {
    if (file && !this.disabled) {
      if (this.primefileUpload?.msgs?.length > 0) {
        const msg = this.primefileUpload.msgs[0];
        return this.notificationService.modalConfirmAction({
          title: msg?.detail,
          text: msg?.summary,
          icon: 'info',
          showCancelButton: false,
          confirmButtonText: 'Aceptar',
        });
      }
      const selectedFile = file.currentFiles?.[0];
      if (selectedFile) {
        this.plainFile = new File([selectedFile], selectedFile.name, {
          type: selectedFile.type,
        });
        this.onEmittPlainFile.emit(this.plainFile);
      }
      this.primefileUpload.clear();
    }
  }
}
