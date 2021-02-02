package org.ftn.upp.lass.service;

import org.ftn.upp.lass.dto.request.FormSubmissionRequest;
import org.ftn.upp.lass.exception.BadRequestException;

public interface FormManagementService {

    String submitForm(FormSubmissionRequest formSubmissionRequest, String targetProcessVariable) throws BadRequestException;

    String submitFormWithoutAssignee(FormSubmissionRequest formSubmissionRequest, String targetProcessVariable) throws BadRequestException;
}
