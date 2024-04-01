import { Component, Input, ViewChild } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-generic-table',
  standalone: true,
  imports: [AgGridAngular, AgGridModule],
  templateUrl: './generic-table.component.html',
  styleUrl: './generic-table.component.css',
})
export class GenericTableComponent {
  @Input() rowData: any[] = [];
  @Input() colDefs: ColDef[] = [];

  @ViewChild('agGrid') grid!: AgGridAngular;

  ngOnChanges(): void {
    if (this.grid?.api) {
      this.grid.api.setGridOption('rowData', this.rowData);
      this.grid.api.setGridOption('columnDefs', this.colDefs);
    }
  }
}
