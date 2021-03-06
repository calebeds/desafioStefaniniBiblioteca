import { Directive, ElementRef, Host, HostListener } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appPhonemask]'
})
export class PhonemaskDirective {

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
    
    if (backspace && newVal.length <= 6) {
      newVal = newVal.substring(0, newVal.length - 1);
    }

    if(newVal.length === 0) {
      newVal = '';
    } else if( newVal.length <= 2) {
      newVal = newVal.replace(/^(\d{0,2})/, '($1)');
    } else if( newVal.length <= 6) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,4})/, '($1) $2');
    } else if( newVal.length <= 10) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,4})(\d{0,4})/, '($1) $2-$3');
    } else if( newVal.length <= 11) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,5})(\d{0,4})/, '($1) $2-$3');
    } else {
      newVal = newVal.substring(0, 11);
      newVal = newVal.replace(/^(\d{0,2})(\d{0,5})(\d{0,4})/, '($1) $2-$3');
    }

    this.ngControl.valueAccessor?.writeValue(newVal);
  }
}
