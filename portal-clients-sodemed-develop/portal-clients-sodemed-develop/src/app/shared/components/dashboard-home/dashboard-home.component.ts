import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, type OnInit } from '@angular/core';

@Component({
    selector: 'app-dashboard-home',
    standalone: true,
    template: `
            <div class="tw-w-full tw-h-full tw-flex tw-flex-col" style="height: calc(100vh - 200px);">
                     <div class="tw-flex tw-flex-col tw-justify-center tw-items-start">
                             <p class="tw-font-bold tw-text-3xl tw-text-stone-600 my-1">Bienvenido</p>
                             <p class="tw-text-stone-600 tw-text-2xl  my-1">Al Portal De Clientes</p>
                        </div>
                        <div  class="tw-flex tw-flex-1 tw-justify-center tw-items-center">
                            <picture style="aspect-ratio: auto 768 / 227;">
                                <img  src="../../../../assets/layout/images/image_pricipal_logo_medcol.png"  alt="Medcol Logo" srcset="">
                            </picture>
                        </div>
                </div>
    `,
    styleUrl: './dashboard-home.component.scss',
    imports: [CommonModule],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export default class DashboardHomeComponent implements OnInit {
    ngOnInit(): void { }
}
