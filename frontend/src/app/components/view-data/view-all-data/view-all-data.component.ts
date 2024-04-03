import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ColDef } from 'ag-grid-community';
import { ParsedDataService } from '../../../services/parsed-data/parsed-data.service';
import { ParsedDataContainer } from '../../../utils/types';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';

@Component({
  selector: 'app-view-all-data',
  standalone: true,
  templateUrl: './view-all-data.component.html',
  styleUrl: './view-all-data.component.css',
  imports: [GenericTableComponent, NgIf, NgFor],
})
export class ViewAllDataComponent implements OnInit {
  tableData: any[] = [];

  error?: string;

  parsedData: ParsedDataContainer[] = [];
  constructor(private parsedDataService: ParsedDataService) {}
  ngOnInit(): void {
    this.parsedDataService.fetchAll().subscribe((data) => {
      data.map((data) => {
        this.tableData?.push({ ...this.populate([data]), name: data.id });
      });
    });
  }

  private populate(data: ParsedDataContainer[]) {
    let rowData: any[] = [];
    let colDefs: ColDef[] = [];
    if (data.length > 0) {
      colDefs = Object.keys(data[0].parsedData[0]).map((key) => ({
        field: key,
      }));
      data.forEach((parsedDataContainer) =>
        rowData.push(...parsedDataContainer.parsedData)
      );
    } else {
      colDefs = [];
      rowData = [];
    }
    return [rowData, colDefs];
  }
}
