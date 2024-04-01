import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecSelectionComponent } from './spec-selection.component';

describe('SpecSelectionComponent', () => {
  let component: SpecSelectionComponent;
  let fixture: ComponentFixture<SpecSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpecSelectionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpecSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
