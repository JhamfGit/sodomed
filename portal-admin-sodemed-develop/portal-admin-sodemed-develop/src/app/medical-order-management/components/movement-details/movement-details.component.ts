import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { MovementsMedicalRequest } from '@medication-request/models/movements-medical-request';
import { DictionaryStatusOrderPipe } from '@medication-request/pipes/dictionary-status-order.pipe';
import { SharedConstants } from '@shared/models/shared-constants';
import { DividerModule } from 'primeng/divider';

@Component({
    selector: 'app-movement-details',
    templateUrl: './movement-details.component.html',
    styleUrl: './movement-details.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        DictionaryStatusOrderPipe,
        DividerModule
    ],
})
export class MovementDetailsComponent {

    @Input({ required: true }) movementsMedicalRequest: MovementsMedicalRequest[] = []

    constructor() { }

    get FORMAT_DATE() {
        return SharedConstants.FORMAT_DATE_W_HOURS;
    }
}
