package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.ftn.upp.lass.service.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SendUpdatedMembershipRequestNotification implements JavaDelegate {

    private final NotificationService notificationService;
    private final MembershipRequestRepository membershipRequestRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SendUpdatedMembershipRequestNotification.class.getName());

        // TODO: Implement

        log.info(LogMessages.FINISHED, SendUpdatedMembershipRequestNotification.class.getName());
    }
}