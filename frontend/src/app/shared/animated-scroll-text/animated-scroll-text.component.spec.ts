import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimatedScrollTextComponent } from './animated-scroll-text.component';

describe('AnimatedScrollTextComponent', () => {
  let component: AnimatedScrollTextComponent;
  let fixture: ComponentFixture<AnimatedScrollTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimatedScrollTextComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimatedScrollTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
