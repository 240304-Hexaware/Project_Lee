import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParsingHistoryComponent } from './parsing-history.component';

describe('ParsingHistoryComponent', () => {
  let component: ParsingHistoryComponent;
  let fixture: ComponentFixture<ParsingHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParsingHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParsingHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
