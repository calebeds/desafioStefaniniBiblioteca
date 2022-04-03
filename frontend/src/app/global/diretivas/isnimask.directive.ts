import { Directive, ElementRef, Host, HostListener } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appIsnimask]'
})
export class IsnimaskDirective {

  constructor(private ngControl: NgControl) { }

  @HostListener('ngModelChange', ['$event'])
  onModelChange(event:any) {
    this.onInputChange(event, false);
  }

  @HostListener('keydown.backspace', ['$event'])
  keydownBackspace(event:any) {
    this.onInputChange(event.target.value, true);
  }

  onInputChange(event: any, backspace: boolean) {
    let newVal = event.replace(/\D/g, '');
    
    
    // if (backspace && newVal.length <= 17) {
    //   newVal = newVal.substring(0, newVal.length);
    // }

    if(newVal.length === 0) {
      newVal = '';
    } else if( newVal.length < 16) {
      newVal = newVal.substring(0, 15);
      newVal = newVal.replace(/^(\d{0,15})/, '$1');
    } else {
      newVal = newVal.substring(0, 15);
      newVal = newVal.replace(/^(\d{0,15})/, '$1');
    }
    

    this.ngControl.valueAccessor?.writeValue(newVal);
  }
}
