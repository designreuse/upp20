package ftn.upp.literary.association.handler;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PickGenreListener implements TaskListener {

    private final FormService formService;

    public PickGenreListener(FormService formService) {
        this.formService = formService;
    }

    public void notify(DelegateTask delegateTask) {
        // CheckMe if you think of a better way, change this
        TaskFormData taskFormData = formService.getTaskFormData(delegateTask.getId());
        DelegateExecution execution = delegateTask.getExecution();
        FormField formField = taskFormData.getFormFields().get(0);
        Map<String, String> genresMap = (Map<String, String>) formField.getType().getInformation("values");
        execution.setVariable("genres", genresMap);
    }
}
