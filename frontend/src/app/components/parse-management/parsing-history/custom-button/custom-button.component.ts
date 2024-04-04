import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';
import { ParsedDataService } from '../../../../services/parsed-data/parsed-data.service';
import { Metadata } from '../../../../utils/types';
import { download } from '../../../../utils/utils';

@Component({
  selector: 'app-custom-button',
  standalone: true,
  imports: [],
  templateUrl: './custom-button.component.html',
  styleUrl: './custom-button.component.css',
})
export class CustomButtonComponent implements ICellRendererAngularComp {
  parsedId?: string;
  constructor(
    private router: Router,
    private parsedDataService: ParsedDataService
  ) {}
  agInit(params: ICellRendererParams): void {
    this.parsedId = (params.data as Metadata).parsedData as unknown as string;
  }
  refresh(params: ICellRendererParams) {
    return true;
  }
  onClickView() {
    this.router.navigate(['/view'], {
      queryParams: {
        parsedId: this.parsedId,
      },
    });
  }

  onClickDownload() {
    if (!this.parsedId) {
      return;
    }
    this.parsedDataService
      .fetchAllByParsedDataId(this.parsedId)
      .subscribe((data) => {
        const parsedDataWithoutMetadata = data
          .map((data) => data.parsedData)
          .flat();
        const parsedDataJson = JSON.stringify(
          parsedDataWithoutMetadata,
          null,
          2
        );
        const filename = `${new Date().toISOString()}.txt`;
        download(parsedDataJson, filename);
      });
  }
}
