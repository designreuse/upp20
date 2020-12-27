package ftn.upp.literary.association.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;

@Service
public class EmailService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {

        // Todo sendEmail
    }
}
