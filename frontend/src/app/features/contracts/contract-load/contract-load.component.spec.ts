import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContractLoadComponent } from './contract-load.component';

describe('ContractLoadComponent', () => {
  let component: ContractLoadComponent;
  let fixture: ComponentFixture<ContractLoadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContractLoadComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContractLoadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
