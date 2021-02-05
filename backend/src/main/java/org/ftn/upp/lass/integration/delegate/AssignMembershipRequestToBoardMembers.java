package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.repository.BoardMemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssignMembershipRequestToBoardMembers implements JavaDelegate {

    private final BoardMemberRepository boardMemberRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, AssignMembershipRequestToBoardMembers.class.getName());

        // TODO: Implement

        execution.setVariable("assignedBoardMembers", this.boardMemberRepository.findAll());


        log.info(LogMessages.FINISHED, AssignMembershipRequestToBoardMembers.class.getName());
    }
}