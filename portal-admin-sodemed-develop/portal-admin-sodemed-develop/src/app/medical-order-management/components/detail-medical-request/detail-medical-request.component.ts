import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, type OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ResponseOrders } from '@medication-request/models/response-orders';
import { SharedConstants } from '@shared/models/shared-constants';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { IconFieldModule } from 'primeng/iconfield';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DividerModule } from 'primeng/divider';
import { DictionaryStatusOrderPipe } from '@medication-request/pipes/dictionary-status-order.pipe';
import { AccordionModule } from 'primeng/accordion';
import { DialogModule } from 'primeng/dialog';
import { StatusOrder, StatusOrderOptions, StatusOrderType } from '@shared/models/status-order';
import { DataSetUpManagement } from '@medication-request/models/data-set-up-management';
import { BaseAccordionTapComponent } from '@shared/components/base-accordion-tap/base-accordion-tap.component';
import { MovementsMedicalRequest } from '@medication-request/models/movements-medical-request';
import { MovementDetailsComponent } from "../movement-details/movement-details.component";
import { OrderStatusColorPipe } from '@shared/pipes/get-styles-status-order.pipe';
import { DataFileResource } from '@shared/models/data-file-resource';
import { EnumStatusUser } from 'src/app/user-management/models/status-user';
import { EnumStatusRole } from 'src/app/user-management/models/role';
import { DictionaryTypeClientPipe } from '@medication-request/pipes/dictionary-type-client.pipe';
import { RadioButtonModule } from 'primeng/radiobutton';
import { NumberValidatorDirective } from '@shared/directives/number-validator.directive';

@Component({
    selector: 'app-detail-medical-request',
    templateUrl: './detail-medical-request.component.html',
    styleUrl: './detail-medical-request.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        ButtonModule,
        InputTextModule,
        IconFieldModule,
        InputIconModule,
        InputGroupModule,
        InputGroupAddonModule,
        DropdownModule,
        InputTextareaModule,
        DividerModule,
        DictionaryStatusOrderPipe,
        AccordionModule,
        DialogModule,
        BaseAccordionTapComponent,
        MovementDetailsComponent,
        OrderStatusColorPipe,
        DictionaryTypeClientPipe,
        RadioButtonModule,
        NumberValidatorDirective
    ],
})
export class DetailMedicalRequestComponent implements OnInit {
    @Input() loadingSetUpManagement: boolean = false;
    @Input() medicalRequestDetails: ResponseOrders;
    @Input() asdasdasda: boolean = false;
    @Input() showDialogSetUpManagement: boolean = false;
    @Input() movementsMedicalRequest: MovementsMedicalRequest[] = []
    @Output() onSetUpManagement: EventEmitter<DataSetUpManagement> = new EventEmitter<DataSetUpManagement>();
    @Output() onCancelManagement = new EventEmitter();
    @Output() onGetResource = new EventEmitter<DataFileResource>();

    public readonly FORMAT_DATE = SharedConstants.FORMAT_DATE
    public typesStatusOrder: { label: string, value: StatusOrderType }[];
    public statusOrder = StatusOrder;
    public valueTypeSend: 'partial' | 'complete' = 'complete'
    private _form: FormGroup;
    private statusOrderOptions: StatusOrderOptions = new StatusOrderOptions()
    ngOnInit(): void {
        this.typesStatusOrder = this.statusOrderOptions.statusOrderOptionManagement
        this.valueTypeSend = 'complete'
    }

    constructor(
        private fb: FormBuilder,
    ) { }


    get form(): FormGroup {
        if (this._form == null) {
            this._form = this.fb.group({
                stateOrderByAdmin: ['', [Validators.required]],
                descriptionByAdmin: ['', Validators.required],
                partial: [false],
                totalPending: [1],
                detailsPending: [''],
            })
        }
        return this._form;
    }

    /**
     * devuelve el valor del formulario
     */
    get formRaw(): DataSetUpManagement {
        return this.form.getRawValue();
    }

    cancelManagement() {
        this.resetForm();
        this.onCancelManagement.emit()
    }

    save() {
        if (!this.form.valid) {
            return;
        }
        this.onSetUpManagement.emit(this.formRaw)
        this.resetForm()
    }
    hidenDialogManagement() {
        this.resetForm()
        this.onCancelManagement.emit()
    }
    showDoc(fileName: string, path: string) {
        this.onGetResource.emit({
            fileName: fileName,
            path: path
        })
    }

    compled(event) {
        this.removeValidatorFieldSendPartial();
    }

    partial(event) {
        this.form.get('partial').setValidators(Validators.required)
        this.form.get('totalPending').setValidators(Validators.required)
        this.form.get('detailsPending').setValidators(Validators.required)
        this.form.get('detailsPending').markAsPristine()
        this.form.get('detailsPending').updateValueAndValidity()
        this.form.get('partial').setValue(true)
    }

    removeValidatorFieldSendPartial() {
        this.form.get('partial').clearValidators()
        this.form.get('totalPending').clearValidators()
        this.form.get('detailsPending').clearValidators();
        this.form.get('detailsPending').updateValueAndValidity()
        this.form.get('partial').setValue(false)
        this.form.updateValueAndValidity()
    }



    changeState(stateSelected: StatusOrderType) {
        if (stateSelected !== StatusOrder.Send) {
            this.removeValidatorFieldSendPartial();
            return
        }
    }

    resetForm() {
        this.valueTypeSend = 'complete'
        this.compled(null);
        this.form.patchValue({
            stateOrderByAdmin: '',
            descriptionByAdmin: '',
            partial: false,
            totalPending: 1,
            detailsPending: '',
        })
        this.form.markAsPristine();
    }
}
