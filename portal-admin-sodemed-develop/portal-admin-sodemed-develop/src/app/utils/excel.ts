import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';
 
 
export const exportExcel = (arrayToPrint: Record<string, any>[], nameDocument: string) => {
    const worksheet = XLSX.utils.json_to_sheet(arrayToPrint);
    const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const blob: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    FileSaver.saveAs(blob, nameDocument);
}