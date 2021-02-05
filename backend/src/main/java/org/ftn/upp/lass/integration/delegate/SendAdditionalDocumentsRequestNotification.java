package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.service.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SendAdditionalDocumentsRequestNotification implements JavaDelegate {

    private final NotificationService notificationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SendAdditionalDocumentsRequestNotification.class.getName());

        // TODO: Implement

        log.info(LogMessages.FINISHED, SendAdditionalDocumentsRequestNotification.class.getName());
    }
}