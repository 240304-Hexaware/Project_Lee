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

@Component({
  selector: 'app-flat-file-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './flat-file-history.component.html',
  styleUrl: './flat-file-history.component.css',
})
export class FlatFileHistoryComponent implements OnChanges, OnInit {
  files: FlatFile[] = [];

  selectedFlatFileDetail?: string;

  @Input() flatFileData?: FlatFile;

  constructor(private FlatFileService: FlatFileService) {}
  ngOnInit(): void {
    this.loadHistory();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['flatFileData'] && this.flatFileData) {
      this.files.push(this.flatFileData);
    }
  }

  loadHistory() {
    this.FlatFileService.getAllFlatFile().subscribe({
      next: (data) => {
        this.files = data;
      },
    });
  }

  extractDate(id: string): string {
    const parsed = JSON.parse(JSON.stringify(id));
    return parsed['date'];
  }
  handleClickFlatFile(id: string) {
    const found = this.files.find((file) => file.id == id);
    if (found != null) {
      this.selectedFlatFileDetail = this.getDetail(found);
    }
  }

  private getDetail(flatfile: FlatFile): string {
    let strs = [];
    strs.push(
      `filename: ${flatfile.fileName}`,
      `uploaded_at ${new Date(this.extractDate(flatfile.id))}`,
      `uploader_id: ${flatfile.userId}`,
      `parsed: ${flatfile.metaDataId != null ? 'Y' : 'N'}`
    );
    return strs.join('\r\n');
  }
}
