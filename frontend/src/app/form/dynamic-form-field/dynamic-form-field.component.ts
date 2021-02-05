import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {FormFieldBase} from 'src/app/model/form-field-base';

@Component({
  selector: 'app-dynamic-form-field',
  templateUrl: './dynamic-form-field.component.html',
  styleUrls: ['./dynamic-form-field.component.scss']
})
export class DynamicFormFieldComponent implements OnInit {

  @Input() field!: FormFieldBase<string>;
  @Input() form!: FormGroup;

  get isValid(): any {
    return this.form.controls[this.field.id].valid;
  }

  constructor() {
  }

  ngOnInit(): void {

  }

}
