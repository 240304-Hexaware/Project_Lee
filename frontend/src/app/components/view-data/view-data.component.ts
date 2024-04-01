import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { ParsedDataService } from '../../services/parsed-data/parsed-data.service';
import { ParsedDataContainer } from '../../utils/types';
import { ModalComponent } from '../modal/modal.component';
import { GenericTableComponent } from '../shared/generic-table/generic-table.component';
import { SpecSelectionComponent } from './spec-selection/spec-selection.component';

@Component({
  selector: 'app-view-data',
  standalone: true,
  templateUrl: './view-data.component.html',
  styleUrl: './view-data.component.css',
  imports: [
    AgGridAngular,
    AgGridModule,
    ModalComponent,
    GenericTableComponent,
    SpecSelectionComponent,
    NgIf,
  ],
})
export class ViewDataComponent {
  parsedData: ParsedDataContainer[] = [];

  rowData: any[] = [];
  colDefs: ColDef[] = [];

  error?: string;

  constructor(private parsedDataService: ParsedDataService) {}

  handleSpecSelected(specId: string) {
    this.error = '';
    // TODO: pagination
    this.parsedDataService.getAllBySpec(specId).subscribe({
      next: (data) => {
        this.populate(data);
      },
      error: (_) => {
        this.error = 'failed to fetch data';
      },
    });
  }

  private populate(data: ParsedDataContainer[]) {
    if (data.length > 0) {
      this.colDefs = Object.keys(data[0].parsedData[0]).map((key) => ({
        field: key,
      }));
      data.forEach((parsedDataContainer) =>
        this.rowData.push(...parsedDataContainer.parsedData)
      );
    } else {
      this.colDefs = [];
      this.rowData = [];
    }
  }
}
