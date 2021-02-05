package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.ftn.upp.lass.common.LogMessages;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SetSubmittedDocumentsCount implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SetSubmittedDocumentsCount.class.getName());

        // TODO: Implement - for now only mocked to pass, in future retrieve the submitted documents from the form and count them
        execution.setVariable("submittedDocumentsCount", 2);

        log.info(LogMessages.FINISHED, SetSubmittedDocumentsCount.class.getName());
    }
}