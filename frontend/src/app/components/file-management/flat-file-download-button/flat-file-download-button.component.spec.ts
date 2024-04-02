import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatFileDownloadButtonComponent } from './flat-file-download-button.component';

describe('FlatFileDownloadButtonComponent', () => {
  let component: FlatFileDownloadButtonComponent;
  let fixture: ComponentFixture<FlatFileDownloadButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlatFileDownloadButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlatFileDownloadButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
