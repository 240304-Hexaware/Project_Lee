import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllDataComponent } from './view-all-data.component';

describe('ViewAllDataComponent', () => {
  let component: ViewAllDataComponent;
  let fixture: ComponentFixture<ViewAllDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllDataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAllDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
