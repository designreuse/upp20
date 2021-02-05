package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.ReviewResult;
import org.ftn.upp.lass.repository.BoardMemberRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.ftn.upp.lass.repository.MembershipReviewRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HandleMembershipRequestReviewSubmission implements TaskListener {

    private final BoardMemberRepository boardMemberRepository;
    private final MembershipRequestRepository membershipRequestRepository;
    private final MembershipReviewRepository membershipReviewRepository;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info(LogMessages.EXECUTE, HandleMembershipRequestReviewSubmission.class.getName());

        final var justification = (String) delegateTask.getVariable(Constants.FormFieldVariables.JUSTIFICATION);
        final var reviewResultVariableValue = (String) delegateTask.getVariable(Constants.FormFieldVariables.REVIEW_RESULT);
        final var reviewResult = this.extractReviewResultFromVariableValue(reviewResultVariableValue);
        final var assignedBoardMemberOptional = this.boardMemberRepository.findBoardMemberByUsername(delegateTask.getAssignee());
        if (assignedBoardMemberOptional.isPresent()) {
            final var assignedBoardMember = assignedBoardMemberOptional.get();
            var membershipRequest = (MembershipRequest) delegateTask.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
            final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
            if (membershipRequestOptional.isPresent()) {
                membershipRequest = membershipRequestOptional.get();
                final var membershipReviewOptional = this.membershipReviewRepository.findMembershipReviewByRevieweeAndMembershipRequest(assignedBoardMember, membershipRequest);
                if (membershipReviewOptional.isPresent()) {
                    final var membershipReview = membershipReviewOptional.get();

                    membershipReview.setJustification(justification);
                    membershipReview.setReviewResult(reviewResult);
                    membershipReview.setLastChangedAt(LocalDateTime.now());

                    this.membershipReviewRepository.save(membershipReview);

                    if (reviewResult == ReviewResult.RESUBMISSION_REQUIRED) {
                        membershipRequest.setResubmissionDeadline(LocalDateTime.now().plusDays(Constants.ProcessConstants.RESUBMISSION_DEADLINE_DAYS_COUNT));
                        membershipRequest.setReviewResult(reviewResult);
                        this.membershipRequestRepository.save(membershipRequest);
                    }
                }
            }
        }

        log.info(LogMessages.FINISHED, HandleMembershipRequestReviewSubmission.class.getName());
    }

    private ReviewResult extractReviewResultFromVariableValue(String reviewResultVariableValue) {
        switch (reviewResultVariableValue) {
            case Constants.ProcessConstants.REVIEW_RESULT_ACCEPTED:
                return ReviewResult.ACCEPTED;
            case Constants.ProcessConstants.REVIEW_RESULT_REJECTED:
                return ReviewResult.REJECTED;
            case Constants.ProcessConstants.REVIEW_RESULT_RESUBMISSION_REQUIRED:
                return ReviewResult.RESUBMISSION_REQUIRED;
            default:
                return ReviewResult.IN_PROGRESS;
        }
    }
}
