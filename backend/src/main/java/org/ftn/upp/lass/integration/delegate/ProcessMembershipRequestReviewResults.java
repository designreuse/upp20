package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.LogMessages;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProcessMembershipRequestReviewResults implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, ProcessMembershipRequestReviewResults.class.getName());

        // TODO: Implement

        log.info(LogMessages.FINISHED, ProcessMembershipRequestReviewResults.class.getName());
    }
}