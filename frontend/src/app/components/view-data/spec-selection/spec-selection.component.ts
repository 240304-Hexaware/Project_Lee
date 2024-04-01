import { NgFor } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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

  @Output() specSelected = new EventEmitter<string>();

  constructor(private specFileService: SpecFileService) {}
  ngOnInit(): void {
    this.specFileService.getAllSpecs().subscribe((data) => (this.specs = data));
  }

  onSelect(event: any) {
    const selectedSpecId = event.target.value;
    if (!selectedSpecId) {
      return;
    }
    this.specSelected.emit(selectedSpecId);
  }
}
