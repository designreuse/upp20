import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {FormFieldBase} from 'src/app/model/form-field-base';
import {FormService} from 'src/app/services/form.service';
import {FormSubmissionType} from '../../../model/form-submission-type';
import {TaskInfoResponse} from '../../../model/response/task-info-response';
import Swal from 'sweetalert2';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.scss']
})
export class DynamicFormComponent implements OnInit {

  @Input() fields: FormFieldBase<any>[] = [];
  @Output() newItemEvent = new EventEmitter<any>();

  form!: FormGroup;

  constructor(private formService: FormService) {
  }

  ngOnInit(): void {
    this.form = this.formService.toFormGroup(this.fields);
    console.log('FormGroup', this.form);
  }

  onSubmit(): void {
    // todo see how to handle all form submissions
    this.newItemEvent.emit(this.form.value);
  }
}
