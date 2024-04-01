import { Component, OnInit, ViewChild } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { MetadataService } from '../../../services/metadata/metadata.service';
import { ModalComponent } from '../../modal/modal.component';

@Component({
  selector: 'app-parsing-history',
  standalone: true,
  templateUrl: './parsing-history.component.html',
  styleUrl: './parsing-history.component.css',
  imports: [AgGridAngular, AgGridModule, ModalComponent],
})
export class ParsingHistoryComponent implements OnInit {
  rowData: any[] = [];
  colDefs: ColDef[] = [];

  showModal = false;

  @ViewChild('parsingHistoryGrid') grid!: AgGridAngular;

  constructor(private metaDataService: MetadataService) {}

  ngOnInit(): void {
    this.metaDataService.getMetadata().subscribe((data) => {
      if (data.length == 0) {
        this.grid.api.setGridOption('columnDefs', []);
        this.grid.api.setGridOption('rowData', []);
      }
      Object.keys(data[0]).forEach((key) => this.colDefs.push({ field: key }));
      this.grid.api.setGridOption('columnDefs', this.colDefs);

      data.forEach((metadata) => {
        this.rowData.push({
          id: metadata.id,
          flatFile: metadata.flatFile.fileName,
          parsedData: metadata.parsedData.id,
          specFile: metadata.specFile.name,
          parsedAt: new Date(metadata.parsedAt).toLocaleDateString(),
        });

        this.grid.api.setGridOption('rowData', this.rowData);
        this.grid.api.sizeColumnsToFit({
          defaultMinWidth: 100,
        });
      });
    });
  }
}
