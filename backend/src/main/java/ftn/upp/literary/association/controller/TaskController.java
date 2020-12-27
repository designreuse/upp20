package ftn.upp.literary.association.controller;

import ftn.upp.literary.association.model.dto.FormSubmissionDto;
import ftn.upp.literary.association.util.FormUtil;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final IdentityService identityService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final FormService formService;

    public TaskController(IdentityService identityService, RuntimeService runtimeService, TaskService taskService, FormService formService) {
        this.identityService = identityService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.formService = formService;
    }

    @PostMapping("/{taskId}/submit-form")
    public ResponseEntity submitForm(@RequestBody List<FormSubmissionDto> formSubmissions, @PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        runtimeService.setVariable(task.getProcessInstanceId(), "formSubmissions", formSubmissions);
        formService.submitTaskForm(taskId, FormUtil.convertFormSubmissionsToMap(formSubmissions));
        return ResponseEntity.ok().build();
    }
}
