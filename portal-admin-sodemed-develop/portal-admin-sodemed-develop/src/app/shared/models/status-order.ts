export enum StatusOrder {
    Pending = 'pending',
    Processing = 'processing',
    Accept = 'accept',
    Send = 'send',
    Reject = 'reject',
    Delivered = 'delivered'
}


export type StatusOrderType = keyof typeof StatusOrder | typeof StatusOrder[keyof typeof StatusOrder];


export class StatusOrderOptions {
    private statusOrderOptions: { label: string, value: StatusOrderType }[];

    constructor() {
        this.statusOrderOptions = [
            { label: "Selecciona el estado de la orden", value: null },
            { label: "Pendiente", value: StatusOrder.Pending },
            { label: "Procesando", value: StatusOrder.Processing },
            { label: "Aceptado", value: StatusOrder.Accept },
            { label: "Rechazado", value: StatusOrder.Reject },
            { label: "Enviado", value: StatusOrder.Send },
            { label: "Entregado", value: StatusOrder.Delivered }
        ];
    }

    get statusOrderOptionsReturn(): { label: string, value: StatusOrderType }[] {
        return this.statusOrderOptions;
    }
    get statusOrderOptionManagement(): { label: string, value: StatusOrderType }[] {
        return this.statusOrderOptions.filter(state => state.value !== StatusOrder.Pending );
    }
}