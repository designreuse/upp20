import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable, of} from 'rxjs';
import {FormFieldBase} from 'src/app/model/form-field-base';
import {FormService} from 'src/app/services/form.service';
import {ProcessService} from '../services/process.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProcessConstants} from '../model/process-constants';
import {ProcessInfoResponse} from '../model/response/process-info-response';
import {TaskService} from '../services/task.service';
import {FormFieldsResponse} from '../model/response/form-fields-response';
import {FormSubmissionType} from '../model/form-submission-type';
import {TaskInfoResponse} from '../model/response/task-info-response';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  private processInstanceId: any = localStorage.getItem('processInstanceId');

  private formFields: any = {};
  formFields$: Observable<FormFieldBase<any>[]> | undefined;

  constructor(private processService: ProcessService,
              private formService: FormService,
              private taskService: TaskService,
              private route: ActivatedRoute,
              private router: Router) {

    if (!this.processInstanceId && route.toString().includes('reader')) {
      processService.startProcessInstance(ProcessConstants.REGISTER_READER_PROCESS).subscribe((response: ProcessInfoResponse) => {
        localStorage.setItem('processInstanceId', response.processInstanceId);
        this.processInstanceId = response.processInstanceId;
        this.getForm();
      });
    } else if (!this.processInstanceId) {
      // todo for author
    } else {
      this.getForm();
    }
  }

  ngOnInit(): void {
  }

  getForm(): void {
    this.taskService.getCurrentlyActiveTaskForm(this.processInstanceId).subscribe((response: FormFieldsResponse) => {
      // todo maybe if processInstanceId doesn't exist, or it's different, show error?
      localStorage.setItem('taskId', response.taskId);
      console.log('response', response);
      response.formFields.forEach((field: FormFieldBase<any>) => {
        this.formFields[field.id] = new FormControl(
          field.defaultValue,
          null
          // field.validationConstraints.length > 0 ? this.formService.generateValidations(field.validationConstraints) : null
        );
      });

      this.formFields$ = of(response.formFields);
    });
  }

  submitForm(event: any): void {
    this.formService.submitFormWithoutAsignee(event, FormSubmissionType.USER_DATA_FORM).subscribe(
      (response: TaskInfoResponse) => {
        localStorage.setItem('taskId', response.taskId);
        localStorage.setItem('processInstanceId', response.processInstanceId);

        // todo if beta reader is checked, get another form

        Swal.fire({
          icon: 'success',
          title: 'You have successfully registered as a reader!',
          allowEnterKey: true,
          allowOutsideClick: false,
          confirmButtonText: 'Okay'
        }).then(() => this.router.navigate(['/']));
      }, error => console.log(error));
  }
}
