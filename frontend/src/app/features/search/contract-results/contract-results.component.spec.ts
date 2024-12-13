import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractResultsComponent } from './contract-results.component';

describe('ContractResultsComponent', () => {
  let component: ContractResultsComponent;
  let fixture: ComponentFixture<ContractResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContractResultsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContractResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
