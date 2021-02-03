package org.ftn.upp.lass.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class FormSubmissionRequest implements Serializable {

    private String taskId;
    private List<FormSubmissionField> formSubmissionFields;
}