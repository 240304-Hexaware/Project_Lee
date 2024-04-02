import { Component, OnInit } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { MetadataService } from '../../../services/metadata/metadata.service';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';
import { ModalComponent } from '../../shared/modal/modal.component';
import { CustomButtonComponent } from './custom-button/custom-button.component';

@Component({
  selector: 'app-parsing-history',
  standalone: true,
  templateUrl: './parsing-history.component.html',
  styleUrl: './parsing-history.component.css',
  imports: [AgGridAngular, AgGridModule, ModalComponent, GenericTableComponent],
})
export class ParsingHistoryComponent implements OnInit {
  rowData: any[] = [];
  colDefs: ColDef[] = [];

  constructor(private metaDataService: MetadataService) {}

  ngOnInit(): void {
    this.metaDataService.getMetadata().subscribe((data) => {
      if (data.length == 0) {
        this.colDefs = [];
        this.rowData = [];
      }
      this.colDefs = Object.keys(data[0]).map((key) => {
        if (key === 'parsedData') {
          return {
            headerName: key,
            cellRenderer: CustomButtonComponent,
          };
        }
        return { field: key };
      });

      data.forEach((metadata) => {
        this.rowData.push({
          id: metadata.id,
          flatFile: metadata.flatFile.fileName,
          parsedData: metadata.parsedData.id,
          specFile: metadata.specFile.name,
          parsedAt: new Date(metadata.parsedAt).toLocaleDateString(),
        });

        // this.grid.api.sizeColumnsToFit({
        //   defaultMinWidth: 100,
        // });
      });
    });
  }
}
