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

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ResetMembershipRequestReviews implements ExecutionListener {

    private final MembershipRequestRepository membershipRequestRepository;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, ResetMembershipRequestReviews.class.getName());

        var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        if (membershipRequestOptional.isPresent()) {
            membershipRequest = membershipRequestOptional.get();
            membershipRequest.getMembershipReviews().forEach(membershipReview -> {
                membershipReview.setReviewResult(ReviewResult.IN_PROGRESS);
                membershipReview.setJustification("Yet to be submitted.");
                membershipReview.setLastChangedAt(LocalDateTime.now());
            });
            membershipRequestRepository.save(membershipRequest);

            execution.setVariable(Constants.ProcessVariables.DECIDED_TO_REJECT, false);
            execution.removeVariable(Constants.FormFieldVariables.REVIEW_RESULT);
        }

        log.info(LogMessages.FINISHED, ResetMembershipRequestReviews.class.getName());
    }
}
