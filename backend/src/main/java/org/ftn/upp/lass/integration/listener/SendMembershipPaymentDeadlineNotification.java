package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.User;
import org.ftn.upp.lass.service.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SendMembershipPaymentDeadlineNotification implements ExecutionListener {

    private final NotificationService notificationService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SendMembershipPaymentDeadlineNotification.class.getName());

        final var user = (User) execution.getVariable(Constants.ProcessVariables.REGISTERED_AUTHOR);
        this.notificationService.sendMembershipPaymentDeadlineEmail(user, execution.getProcessInstanceId());

        log.info(LogMessages.FINISHED, SendMembershipPaymentDeadlineNotification.class.getName());
    }
}
