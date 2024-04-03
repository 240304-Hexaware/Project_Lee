import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ColDef } from 'ag-grid-community';
import { ParsedDataService } from '../../../services/parsed-data/parsed-data.service';
import { ParsedDataContainer } from '../../../utils/types';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';
import { SpecSelectionComponent } from '../spec-selection/spec-selection.component';

@Component({
  selector: 'app-view-all-by-specification',
  standalone: true,
  templateUrl: './view-all-by-specification.component.html',
  styleUrl: './view-all-by-specification.component.css',
  imports: [SpecSelectionComponent, GenericTableComponent, NgIf],
})
export class ViewAllBySpecificationComponent implements OnInit {
  rowData: any[] = [];
  colDefs: ColDef[] = [];

  error?: string;

  parsedData: ParsedDataContainer[] = [];

  constructor(
    private parsedDataService: ParsedDataService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((queryParam) => {
      const parsedId = queryParam['parsedId'];
      const specId = queryParam['specId'];
      if (parsedId && !specId) {
        this.populateWithParsedDataId(queryParam['parsedId']);
      } else if (!parsedId && specId) {
        this.populateWithSpecId(specId);
      } else if (parsedId && specId) {
        // TODO
      }
    });
  }

  populateWithSpecId(specId: string) {
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
  private populateWithParsedDataId(id: string) {
    this.parsedDataService.fetchAllByParsedDataId(id).subscribe({
      next: (data) => this.populate(data),
      error: (_) => {
        this.error = 'failed to fetch data';
      },
    });
  }

  private populate(data: ParsedDataContainer[]) {
    this.rowData = [];
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
