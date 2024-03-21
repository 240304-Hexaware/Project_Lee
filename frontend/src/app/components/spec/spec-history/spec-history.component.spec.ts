import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecHistoryComponent } from './spec-history.component';

describe('SpecHistoryComponent', () => {
  let component: SpecHistoryComponent;
  let fixture: ComponentFixture<SpecHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpecHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpecHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
