import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';
import { Metadata } from '../../../../utils/types';

@Component({
  selector: 'app-custom-button',
  standalone: true,
  imports: [],
  templateUrl: './custom-button.component.html',
  styleUrl: './custom-button.component.css',
})
export class CustomButtonComponent implements ICellRendererAngularComp {
  parsedId?: string;
  constructor(private router: Router) {}
  agInit(params: ICellRendererParams): void {
    this.parsedId = (params.data as Metadata).parsedData as unknown as string;
  }
  refresh(params: ICellRendererParams) {
    return true;
  }
  onClick() {
    this.router.navigate(['/view'], {
      queryParams: {
        parsedId: this.parsedId,
      },
    });
  }
}
