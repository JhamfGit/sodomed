export enum StatusOrder {
    Pending = 'pending',
    Processing = 'processing',
    Accept = 'accept',
    Send = 'send',
    Reject = 'reject',
    Delivered = 'delivered'
}


export type StatusOrderType = keyof typeof StatusOrder |  typeof StatusOrder[keyof typeof StatusOrder];

