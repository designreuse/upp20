package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
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
public class ProcessMembershipRequestReviewResults implements JavaDelegate {

    private final MembershipRequestRepository membershipRequestRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, ProcessMembershipRequestReviewResults.class.getName());

        var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        if (membershipRequestOptional.isPresent()) {
            membershipRequest = membershipRequestOptional.get();
            var totalCount = membershipRequest.getMembershipReviews().size();
            var rejectedCount = membershipRequest.getMembershipReviews().stream()
                    .filter(membershipReview -> membershipReview.getReviewResult().equals(ReviewResult.REJECTED))
                    .count();

            execution.setVariable(Constants.ProcessVariables.DECIDED_TO_REJECT, rejectedCount >= totalCount / 2);
        }

        log.info(LogMessages.FINISHED, ProcessMembershipRequestReviewResults.class.getName());
    }
}