import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {FormFieldBase} from 'src/app/model/form-field-base';
import {FormService} from 'src/app/services/form.service';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.scss']
})
export class DynamicFormComponent implements OnInit {

  @Input() fields: FormFieldBase<any>[] = [];
  @Output() formSubmittedEvent = new EventEmitter<any>();

  form!: FormGroup;

  constructor(private formService: FormService) {
  }

  ngOnInit(): void {
    this.form = this.formService.toFormGroup(this.fields);
    console.log('FormGroup', this.form);
  }

  onSubmit(): void {
    this.formSubmittedEvent.emit(this.form.value);
  }
}
