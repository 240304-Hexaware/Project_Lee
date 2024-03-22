import { Component } from '@angular/core';
import { FlatFileService } from '../../services/flatfile/flatfile.service';
import { FlatFile } from '../../utils/types';
import { FlatFileHistoryComponent } from './flat-file-history/flat-file-history.component';

@Component({
  selector: 'app-file',
  standalone: true,
  templateUrl: './file.component.html',
  styleUrl: './file.component.css',
  imports: [FlatFileHistoryComponent],
})
export class FileComponent {
  status: string = '';
  flatFileDataUploaded?: FlatFile;

  fileToUpload?: File;

  constructor(private flatFileService: FlatFileService) {}
  upload() {
    if (this.fileToUpload == null) {
      this.updateStatus('select file to upload');
      return;
    }
    this.flatFileService.uploadFlatFile(this.fileToUpload).subscribe({
      next: (data) => {
        this.flatFileDataUploaded = data;
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
  handleFileInput(event: any) {
    this.fileToUpload = event.target.files[0];
  }

  private updateStatus(msg: string): void {
    this.status = msg;
  }
}
