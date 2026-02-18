import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, type OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { DropdownModule } from 'primeng/dropdown';
import { StatusOrderOptions, StatusOrderType } from '@shared/models/status-order';
import { FilterMedicalRequestManagement } from '@medication-request/models/filter-medical-request-management.';

@Component({
    selector: 'app-filter-users',
    templateUrl: './filter-users.component.html',
    styleUrl: './filter-users.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        CalendarModule,
        ReactiveFormsModule,
        FormsModule,
        ButtonModule,
        InputTextModule,
        IconFieldModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
        DropdownModule
    ],
})
export class FilterUserComponent implements OnInit {
    @Input() loadingSearch: boolean = false;
    @Output() onCleanFilter = new EventEmitter();
    @Output() onSearch = new EventEmitter<any>();
    private _form: FormGroup;
    constructor(
        private fb: FormBuilder,
    ) { }

    ngOnInit(): void {
    }


    /**
     * crecion del formulario
    */
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                identification: [''],
                idUser: [''],
            })
        }
        return this._form;
    }

    /**
     * devuelve el valor del formulario
     */
    get formRaw(): any {
        return this.form.getRawValue();
    }

    searchUsers() {
        this.onSearch.emit(this.formRaw)
    }

    cleanFilters() {
        this.form.patchValue({
            identification: '',
            idUser: '',
        })
        this.onCleanFilter.emit()
    }
}
