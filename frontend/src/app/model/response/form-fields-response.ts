import {FormFieldBase} from '../form-field-base';

export interface FormFieldsResponse {
  processInstanceId: string;
  taskId: string;
  formFields: FormFieldBase<any>[];
}
