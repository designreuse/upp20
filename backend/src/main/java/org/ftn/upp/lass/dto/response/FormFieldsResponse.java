package org.ftn.upp.lass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormField;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormFieldsResponse {

    private String processInstanceId;
    private String taskId;
    @Builder.Default
    private final List<FormField> formFields = new ArrayList<>();
}