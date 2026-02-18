import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-base-accordion-tap',
    styleUrl: './base-accordion-tap.component.scss',
    standalone: true,
    template: `
    <div class="tw-h-full tw-w-full">
        <ng-content select="[p-accordionTab-base]"></ng-content>
    </div>
    `,
    imports: [
        CommonModule,
    ],
})
export class BaseAccordionTapComponent { }
