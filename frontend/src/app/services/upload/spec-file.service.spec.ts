import { TestBed } from '@angular/core/testing';

import { SpecFileService } from './spec-file.service';

describe('UploadService', () => {
  let service: SpecFileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecFileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
