package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.repository.AuthorRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CreateMembershipRequest implements JavaDelegate {

    private final AuthorRepository authorRepository;
    private final MembershipRequestRepository membershipRequestRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, CreateMembershipRequest.class.getName());

        final var authorUsername = (String) execution.getVariable(Constants.FormFieldVariables.USERNAME);
        final var authorOptional = this.authorRepository.findAuthorByUsername(authorUsername);
        final var coverLetter = (String) execution.getVariable(Constants.FormFieldVariables.COVER_LETTER);
        if (authorOptional.isPresent()) {
            final var membershipRequest = MembershipRequest.builder()
                    .author(authorOptional.get())
                    .coverLetter(coverLetter)
                    .resubmissionDeadline(LocalDateTime.now().plusDays(Constants.ProcessConstants.RESUBMISSION_DEADLINE_DAYS_COUNT))
                    .build();

            this.membershipRequestRepository.save(membershipRequest);
            execution.setVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST, membershipRequest);
        }

        log.info(LogMessages.FINISHED, CreateMembershipRequest.class.getName());
    }
}