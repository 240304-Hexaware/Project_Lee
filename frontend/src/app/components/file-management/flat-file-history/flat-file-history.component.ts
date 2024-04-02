import { CommonModule } from '@angular/common';
import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { FlatFileService } from '../../../services/flatfile/flatfile.service';
import { FlatFile } from '../../../utils/types';
import { FlatFileDownloadButtonComponent } from '../flat-file-download-button/flat-file-download-button.component';

@Component({
  selector: 'app-flat-file-history',
  standalone: true,
  templateUrl: './flat-file-history.component.html',
  styleUrl: './flat-file-history.component.css',
  imports: [CommonModule, FlatFileDownloadButtonComponent],
})
export class FlatFileHistoryComponent implements OnChanges, OnInit {
  files: FlatFile[] = [];

  selectedFlatFileDetail?: string;

  @Input() flatFileDataBeingAdded?: FlatFile;

  error?: string;
  selectedFlatFile?: FlatFile;

  constructor(private flatFileService: FlatFileService) {}
  ngOnInit(): void {
    this.loadHistory();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['flatFileDataBeingAdded'] && this.flatFileDataBeingAdded) {
      this.files.push(this.flatFileDataBeingAdded);
    }
  }

  loadHistory() {
    this.flatFileService.getAllFlatFile().subscribe({
      next: (data) => {
        this.files = data || [];
      },
      error: (error) => {
        console.error('Failed to load flat file history:', error);
        this.error = 'Failed to load flat file history';
      },
    });
  }

  handleClickFlatFile(id: string) {
    const found = this.files.find((file) => file.id == id);
    if (found != null) {
      this.selectedFlatFileDetail = this.getDetail(found);
      this.selectedFlatFile = found;
    }
  }

  private getDetail(flatfile: FlatFile): string {
    let strs = [];
    strs.push(
      `filename: ${flatfile.fileName}`,
      `uploader_id: ${flatfile.userId}`,
      `parsed: ${flatfile.metaDataId != null ? 'Y' : 'N'}`
    );
    return strs.join('\r\n');
  }
}
