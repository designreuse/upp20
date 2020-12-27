package ftn.upp.literary.association.controller;

import ftn.upp.literary.association.model.dto.FormFieldsDto;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final IdentityService identityService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;
    private final FormService formService;

    public RegistrationController(IdentityService identityService, RuntimeService runtimeService, RepositoryService repositoryService, TaskService taskService, FormService formService) {
        this.identityService = identityService;
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.taskService = taskService;
        this.formService = formService;
    }

    @GetMapping("/reader")
    public FormFieldsDto startRegistrationProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("RegisterReader");
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());

        runtimeService.setVariable(processInstance.getId(), "processId", processInstance.getId());

        return new FormFieldsDto(processInstance.getId(), task.getId(), taskFormData.getFormFields());
    }
}
