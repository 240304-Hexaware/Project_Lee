import { Component, Input } from '@angular/core';
import { FlatFileService } from '../../../services/flatfile/flatfile.service';
import { FlatFile } from '../../../utils/types';

@Component({
  selector: 'app-flat-file-download-button',
  standalone: true,
  imports: [],
  templateUrl: './flat-file-download-button.component.html',
  styleUrl: './flat-file-download-button.component.css',
})
export class FlatFileDownloadButtonComponent {
  @Input() fileIdToDownload?: FlatFile;

  constructor(private flatFileService: FlatFileService) {}
  onClick() {
    if (!this.fileIdToDownload) {
      alert('Select flat file to download');
      return;
    }
    this.flatFileService
      .downloadFile(this.fileIdToDownload.id)
      .subscribe((data) => {
        const downloadFile = new Blob([data], { type: 'text/plain' });
        const linkElement = document.createElement('a');
        linkElement.href = URL.createObjectURL(downloadFile);
        linkElement.download = this.fileIdToDownload?.fileName ?? 'flatfile';
        linkElement.click();
        linkElement.remove();
      });
  }
}
