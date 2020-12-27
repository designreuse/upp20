package ftn.upp.literary.association.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.camunda.bpm.engine.form.FormField;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldsDto {

    private String processInstanceId;
    private String taskid;
    private List<FormField> formFields;

}
