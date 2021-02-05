import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {BASE_API_URL, BASE_URL} from '../shared/const';
import {HttpClient} from '@angular/common/http';
import {ProcessConstants} from '../model/process-constants';
import {ProcessInfoResponse} from '../model/response/process-info-response';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  constructor(private httpClient: HttpClient) { }

  startProcessInstance(processName: ProcessConstants): Observable<ProcessInfoResponse> {
    return this.httpClient.get<ProcessInfoResponse>(BASE_API_URL.concat('/process-management/start-process'), {
      params: {
        process_name: processName
      }
    });
  }
}
