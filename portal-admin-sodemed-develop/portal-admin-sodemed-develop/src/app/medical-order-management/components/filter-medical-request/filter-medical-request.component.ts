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
    selector: 'app-filter-medical-request',
    templateUrl: './filter-medical-request.component.html',
    styleUrl: './filter-medical-request.component.scss',
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
export class FilterMedicalRequestComponent implements OnInit {
    @Input() loadingSearch: boolean = false;
    @Output() onCleanFilter = new EventEmitter();
    @Output() onSearch = new EventEmitter<FilterMedicalRequestManagement>();
    private _form: FormGroup;
    public typesStatusOrder: { label: string, value: StatusOrderType }[];
    private statusOrderOptions: StatusOrderOptions = new StatusOrderOptions()
    constructor(
        private fb: FormBuilder,
    ) { }

    ngOnInit(): void {
        this.typesStatusOrder = this.statusOrderOptions.statusOrderOptionsReturn
    }


    /**
     * crecion del formulario
    */
    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                startDate: [''],
                endDate: [''],
                statusOrder: [''],
                identification: [''],
            })
        }
        return this._form;
    }

    /**
     * devuelve el valor del formulario
     */
    get formRaw(): FilterMedicalRequestManagement {
        return this.form.getRawValue();
    }

    searchOrders() {
        this.onSearch.emit(this.formRaw)
    }

    cleanFilters() {
        this.form.patchValue({
            startDate: '',
            endDate: '',
            statusOrder: '',
            identification: '',
        })
        this.onCleanFilter.emit()
    }
}
