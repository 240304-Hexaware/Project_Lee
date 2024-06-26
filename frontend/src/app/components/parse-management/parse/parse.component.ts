import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FlatFileService } from '../../../services/flatfile/flatfile.service';
import { ParsingService } from '../../../services/parsing/parsing.service';
import { SpecFileService } from '../../../services/specfile/spec-file.service';
import {
  ErrorResponse,
  FlatFile,
  ParsingRequestParams,
  Specification,
} from '../../../utils/types';
import { ParsingHistoryComponent } from '../parsing-history/parsing-history.component';
@Component({
  selector: 'app-parse',
  standalone: true,
  templateUrl: './parse.component.html',
  styleUrl: './parse.component.css',
  imports: [NgFor, FormsModule, CommonModule, NgIf, ParsingHistoryComponent],
})
export class ParseComponent implements OnInit {
  selectedSpecId?: string;
  selectedFileId?: string;

  specifications: Specification[] = [];
  flatFiles: FlatFile[] = [];

  parsingResult: string = '';

  error?: string;

  constructor(
    private specFileService: SpecFileService,
    private flatFileService: FlatFileService,
    private parsingService: ParsingService
  ) {}

  ngOnInit(): void {
    this.loadSpecFiles();
    this.loadFlatFiles();
  }

  loadSpecFiles() {
    this.specFileService.getAllSpecs().subscribe({
      next: (data) => {
        this.specifications = data;
      },
      error: (error) => {
        this.updateErrorMessage(error);
      },
    });
  }

  loadFlatFiles() {
    this.flatFileService.getAllFlatFile().subscribe({
      next: (data) => {
        this.flatFiles = data;
      },
      error: (error) => {
        this.updateErrorMessage(error);
      },
    });
  }

  parse() {
    if (this.selectedFileId == null || this.selectedSpecId == null) {
      return;
    }
    this.parsingService
      .parse({
        specId: this.selectedSpecId,
        rawFileId: this.selectedFileId,
      } as ParsingRequestParams)
      .subscribe({
        next: (data) => {
          this.parsingResult = JSON.stringify(data.parsedData, null, 2);
          this.resetInput();
        },
        error: (error: ErrorResponse) => {
          this.updateErrorMessage(
            error.title ?? error.details ?? 'Something went wrong, try again.'
          );
          this.parsingResult = '';
        },
      });
  }
  resetInput() {
    this.selectedFileId = undefined;
    this.selectedSpecId = undefined;
    this.error = undefined;
  }

  private updateErrorMessage(message: string) {
    this.error = message;
  }
}
