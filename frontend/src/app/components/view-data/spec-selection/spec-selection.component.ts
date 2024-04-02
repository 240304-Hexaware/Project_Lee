import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SpecFileService } from '../../../services/specfile/spec-file.service';
import { Specification } from '../../../utils/types';

@Component({
  selector: 'app-spec-selection',
  standalone: true,
  imports: [NgFor],
  templateUrl: './spec-selection.component.html',
  styleUrl: './spec-selection.component.css',
})
export class SpecSelectionComponent implements OnInit {
  specs: Specification[] = [];

  constructor(
    private specFileService: SpecFileService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.specFileService.getAllSpecs().subscribe((data) => (this.specs = data));
  }

  onSelect(event: any) {
    const selectedSpecId = event.target.value;
    if (!selectedSpecId) {
      return;
    }
    this.router.navigate(['/view'], {
      queryParams: {
        specId: selectedSpecId,
      },
    });
  }
}
