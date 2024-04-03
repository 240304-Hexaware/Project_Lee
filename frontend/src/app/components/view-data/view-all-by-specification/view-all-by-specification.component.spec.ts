import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllBySpecificationComponent } from './view-all-by-specification.component';

describe('ViewAllBySpecificationComponent', () => {
  let component: ViewAllBySpecificationComponent;
  let fixture: ComponentFixture<ViewAllBySpecificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllBySpecificationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAllBySpecificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
