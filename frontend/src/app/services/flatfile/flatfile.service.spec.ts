import { TestBed } from '@angular/core/testing';

import { FlatFileService } from './flatfile.service';

describe('FlatfileService', () => {
  let service: FlatFileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlatFileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
