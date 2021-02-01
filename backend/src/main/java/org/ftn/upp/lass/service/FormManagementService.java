package org.ftn.upp.lass.service;

import org.ftn.upp.lass.dto.request.FormSubmissionRequest;

public interface FormManagementService {

    String submitForm(FormSubmissionRequest formSubmissionRequest);
}
