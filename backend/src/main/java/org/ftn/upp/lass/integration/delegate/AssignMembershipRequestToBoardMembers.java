package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.MembershipReview;
import org.ftn.upp.lass.repository.BoardMemberRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.ftn.upp.lass.repository.MembershipReviewRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssignMembershipRequestToBoardMembers implements JavaDelegate {

    private final BoardMemberRepository boardMemberRepository;
    private final MembershipRequestRepository membershipRequestRepository;
    private final MembershipReviewRepository membershipReviewRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, AssignMembershipRequestToBoardMembers.class.getName());

        final var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        if (membershipRequestOptional.isPresent()) {
            final var allBoardMembers = this.boardMemberRepository.findAll();
            allBoardMembers.forEach(boardMember -> {
                final var membershipReviewResult = MembershipReview.builder()
                        .membershipRequest(membershipRequestOptional.get())
                        .reviewee(boardMember)
                        .build();

                this.membershipReviewRepository.save(membershipReviewResult);
            });
            execution.setVariable(Constants.ProcessVariables.ASSIGNED_BOARD_MEMBERS, allBoardMembers);
        }
        log.info(LogMessages.FINISHED, AssignMembershipRequestToBoardMembers.class.getName());
    }
}