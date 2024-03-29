import { CommonModule, NgFor } from '@angular/common';
import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { SpecFileService } from '../../../services/specfile/spec-file.service';
import { Specification } from '../../../utils/types';

@Component({
  selector: 'app-spec-history',
  standalone: true,
  imports: [NgFor, CommonModule],
  templateUrl: './spec-history.component.html',
  styleUrl: './spec-history.component.css',
})
export class SpecHistoryComponent implements OnChanges, OnInit {
  files: Specification[] = [];

  selectedSpecDetail?: string;

  @Input() specData?: Specification;

  constructor(private specFileService: SpecFileService) {}
  ngOnInit(): void {
    this.loadHistory();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['specData'] && this.specData) {
      this.files.push(this.specData);
    }
  }

  loadHistory() {
    this.specFileService.getAllSpecs().subscribe({
      next: (data) => {
        this.files = data;
      },
    });
  }

  extractDate(id: string): string {
    const parsed = JSON.parse(JSON.stringify(id));
    return parsed['date'];
  }

  handleClickSpec(id: string) {
    const found = this.files.find((spec) => spec.id == id);
    if (found != null) {
      this.selectedSpecDetail = JSON.stringify(found.specs, null, 2);
    }
  }
}
