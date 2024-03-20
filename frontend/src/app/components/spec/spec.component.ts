import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SpecFileService } from '../../services/upload/spec-file.service';
import { Specification } from '../../utils/types';

@Component({
  selector: 'app-spec',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './spec.component.html',
  styleUrl: './spec.component.css',
})
export class SpecComponent {
  status: string = '';

  fileToUpload: File | null = null;

  files: Specification[] = [];

  // FIXME: retreived specification from server doesn't have correct id
  constructor(private specFileService: SpecFileService) {
    specFileService.getAllSpecs().subscribe({
      next: (data) => {
        this.files = data;
        console.log('this.files', this.files);
      },
    });
  }

  handleFileInput(event: any) {
    this.fileToUpload = event.target.files[0];
  }

  upload() {
    if (this.fileToUpload == null) {
      this.status = 'Select file to upload';
      return;
    }
    this.specFileService.uploadSpec(this.fileToUpload).subscribe({
      next: (data) => {
        this.files.push(data);
        this.status = 'success';
      },
      error: (error) => {
        if (error instanceof Error) {
          this.status = error.message;
        } else {
          this.status = 'failed';
        }
      },
    });
  }
}
