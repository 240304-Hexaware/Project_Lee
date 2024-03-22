import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SpecFileService } from '../../services/specfile/spec-file.service';
import { Specification } from '../../utils/types';
import { SpecHistoryComponent } from './spec-history/spec-history.component';

@Component({
  selector: 'app-spec',
  standalone: true,
  templateUrl: './spec.component.html',
  styleUrl: './spec.component.css',
  imports: [CommonModule, SpecHistoryComponent],
})
export class SpecComponent {
  status: string = '';

  fileToUpload?: File;

  specDataUploaded?: Specification;

  constructor(private specFileService: SpecFileService) {}

  handleFileInput(event: any) {
    this.fileToUpload = event.target.files[0];
  }

  upload() {
    if (this.fileToUpload == null) {
      this.updateStatus('select file to upload');
      return;
    }
    this.specFileService.uploadSpec(this.fileToUpload).subscribe({
      next: (data) => {
        this.specDataUploaded = data;
        this.updateStatus('succeed to upload');
        this.fileToUpload = undefined;
      },
      error: (error) => {
        if (error instanceof Error) {
          this.updateStatus(error.message);
        } else {
          this.updateStatus('failed to upload');
        }
      },
    });
  }

  private updateStatus(msg: string): void {
    this.status = msg;
  }
}
