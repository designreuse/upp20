package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.repository.BoardMemberRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.ftn.upp.lass.service.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SendMembershipRequestNotification implements JavaDelegate {

    private final NotificationService notificationService;
    private final MembershipRequestRepository membershipRequestRepository;
    private final BoardMemberRepository boardMemberRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SendMembershipRequestNotification.class.getName());

        final var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        final var assignedBoardMemberUsernames = (List<String>) execution.getVariable(Constants.ProcessVariables.ASSIGNED_BOARD_MEMBERS);
        final var assignedBoardMembers = this.boardMemberRepository.findBoardMembersByUsernameIn(assignedBoardMemberUsernames);
        if (membershipRequestOptional.isPresent()) {
            this.notificationService.sendMembershipRequestEmail(assignedBoardMembers, membershipRequestOptional.get(), execution.getProcessInstanceId());
        }

        log.info(LogMessages.FINISHED, SendMembershipRequestNotification.class.getName());
    }
}