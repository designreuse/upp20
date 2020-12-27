package ftn.upp.literary.association.service;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class UserService implements JavaDelegate {

    private final IdentityService identityService;

    public UserService(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) {

        // ToDo validate based on existing users in identityService

        // ToDo save user if valid

        // CheckMe What to do if invalid (investigate best practice handling)

        // ToDo completeTask
    }
}
