import {Injectable} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Constraint, FormFieldBase} from '../model/form-field-base';
import {HttpClient} from '@angular/common/http';
import {BASE_API_URL} from '../shared/const';
import {Observable} from 'rxjs';
import {FormSubmissionType} from '../model/form-submission-type';
import {TaskInfoResponse} from '../model/response/task-info-response';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  private validationHelper = {
    required: Validators.required,
    minlength: Validators.minLength(1),
    maxlength: Validators.maxLength(2),
    min: Validators.min,
    max: Validators.maxLength
  } as any;

  constructor(private httpClient: HttpClient) {
  }

  submitForm(formValue: any, formSubmissionType: FormSubmissionType): Observable<TaskInfoResponse> {
    const formSubmissionRequest = {
      taskId: localStorage.getItem('taskId'),
      formSubmissionFields: this.convertToFormSubmissions(formValue)
    };
    return this.httpClient.post<TaskInfoResponse>(BASE_API_URL.concat('/form-management/submit-form'), formValue, {
      params: {
        form_submission_type: formSubmissionType
      }
    });
  }

  submitFormWithoutAsignee(formValue: any, formSubmissionType: FormSubmissionType): Observable<TaskInfoResponse> {
    const formSubmissionRequest = {
      taskId: localStorage.getItem('taskId'),
      formSubmissionFields: this.convertToFormSubmissions(formValue)
    };
    return this.httpClient.post<TaskInfoResponse>(BASE_API_URL.concat('/form-management/submit-form-without-assignee'),
      formSubmissionRequest, {
        params:
          {
            form_submission_type: formSubmissionType
          }
      });
  }

  private convertToFormSubmissions(formValue: any): any {
    return Array.from(Object.keys(formValue).map(key => {
      return {
        id: key,
        value: formValue[key]
      };
    }));
  }

  toFormGroup(questions: FormFieldBase<string>[]): any {
    const group: any = {};

    questions.forEach(question => {
      group[question.id] = new FormControl('');
    });
    return new FormGroup(group);
  }

  // todo find a way to generate validations

  generateValidations(constraints: Constraint[]): any[] {
    const arr: any[] = [];
    constraints.forEach((constraint: Constraint) => {
      if (!!constraint.configuration) {
        arr.push(this.validationHelper[constraint.name](constraint.configuration));
      } else {
        arr.push(this.validationHelper[constraint.name]);
      }
    });
    return arr;
  }
}
