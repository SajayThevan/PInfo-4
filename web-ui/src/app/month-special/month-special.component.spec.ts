import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthSpecialComponent } from './month-special.component';

describe('MonthSpecialComponent', () => {
  let component: MonthSpecialComponent;
  let fixture: ComponentFixture<MonthSpecialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthSpecialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthSpecialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
