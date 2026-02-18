import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, type OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';

@Component({
    selector: 'app-recorevey-password',
    templateUrl: './recorevey-password.component.html',
    styleUrl: './recorevey-password.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ButtonModule,
        InputTextModule,
        IconFieldModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
    ]
})
export class RecoreveyPasswordComponent implements OnInit {
    private _form: FormGroup;
    @Input() loading: boolean = false;
    @Output() onClosedModal: EventEmitter<any> = new EventEmitter();
    @Output() onRequestChange: EventEmitter<any> = new EventEmitter();
    constructor(
        private fb: FormBuilder,
    ) { }

    ngOnInit(): void { }


    /**
    * Creación del formulario basado en Beneficiary
    */
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                identification: ['', [Validators.required, Validators.minLength(5)]],
            });
        }
        return this._form;
    }

    /**
      * devuelve el valor del formulario
      */
    get formRaw(): { identification: string } {
        return this.form.getRawValue();
    }

    closeModal() {
        this.onClosedModal.emit();
    }

    requestChange() {
        if (!this.form.valid) {
            return
        }
        this.onRequestChange.emit(this.formRaw);
    }


}
