import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TaskInfoResponse} from '../model/response/task-info-response';
import {BASE_API_URL} from '../shared/const';
import {FormFieldsResponse} from '../model/response/form-fields-response';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private httpClient: HttpClient) { }

  claimTask(taskId: string): Observable<TaskInfoResponse> {
    return this.httpClient.get<TaskInfoResponse>(BASE_API_URL.concat('/task-management/claim-task'), {
      params: {
        task_id: taskId
      }
    });
  }

  getCurrentlyActiveTaskForm(processInstanceId: string): Observable<FormFieldsResponse> {
    return this.httpClient.get<FormFieldsResponse>(BASE_API_URL.concat('/task-management/currently-active-task-form'), {
      params: {
        process_instance_id: processInstanceId
      }
    });
  }

  getTaskForm(taskId: string): Observable<FormFieldsResponse> {
    return this.httpClient.get<FormFieldsResponse>(BASE_API_URL.concat('/task-management/task-form'), {
      params: {
        task_id: taskId
      }
    });
  }
}
