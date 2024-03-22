import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatFileHistoryComponent } from './flat-file-history.component';

describe('FlatFileHistoryComponent', () => {
  let component: FlatFileHistoryComponent;
  let fixture: ComponentFixture<FlatFileHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlatFileHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlatFileHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
