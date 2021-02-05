package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.ReviewResult;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SetMembershipRequestStatusToRejected implements ExecutionListener {

    private final MembershipRequestRepository membershipRequestRepository;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SetMembershipRequestStatusToRejected.class.getName());

        var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        if (membershipRequestOptional.isPresent()) {
            membershipRequest = membershipRequestOptional.get();

            membershipRequest.setReviewResult(ReviewResult.REJECTED);
            this.membershipRequestRepository.save(membershipRequest);
        }

        log.info(LogMessages.FINISHED, SetMembershipRequestStatusToRejected.class.getName());
    }
}
