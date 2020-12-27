package ftn.upp.literary.association.controller;

import ftn.upp.literary.association.model.dto.GenresDto;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IdentityService identityService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;
    private final FormService formService;

    public UserController(IdentityService identityService, RuntimeService runtimeService, RepositoryService repositoryService, TaskService taskService, FormService formService) {
        this.identityService = identityService;
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.taskService = taskService;
        this.formService = formService;
    }

    @GetMapping("/{processInstanceId}/genres")
    public ResponseEntity getAvailableGenres(@PathVariable("processInstanceId") String processInstanceId) {
        Map<String, String> genres = (Map<String, String>) runtimeService.getVariable(processInstanceId, "genres");
        return ResponseEntity.ok(new GenresDto(processInstanceId, null, genres));
    }

}
