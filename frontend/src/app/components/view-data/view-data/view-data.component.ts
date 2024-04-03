import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ViewAllBySpecificationComponent } from '../view-all-by-specification/view-all-by-specification.component';
import { ViewAllDataComponent } from '../view-all-data/view-all-data.component';
@Component({
  selector: 'app-view-data',
  standalone: true,
  templateUrl: './view-data.component.html',
  styleUrl: './view-data.component.css',
  imports: [
    ViewAllBySpecificationComponent,
    ViewAllDataComponent,
    NgIf,
    FormsModule,
  ],
})
export class ViewDataComponent implements OnInit {
  selectedViewOption?: 'view-by-specification' | 'view-all';

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe((queryParam) => {
      if (queryParam.has('parsedId')) {
        this.selectedViewOption = 'view-by-specification';
      }
    });
  }
}
