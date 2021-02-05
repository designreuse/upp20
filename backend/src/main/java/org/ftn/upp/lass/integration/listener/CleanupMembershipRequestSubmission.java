package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.Author;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.MembershipStatus;
import org.ftn.upp.lass.repository.AuthorRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CleanupMembershipRequestSubmission implements ExecutionListener {

    private final MembershipRequestRepository membershipRequestRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, CleanupMembershipRequestSubmission.class.getName());

        final var registeredAuthor = (Author) execution.getVariable(Constants.ProcessVariables.REGISTERED_AUTHOR);
        final var authorOptional = this.authorRepository.findAuthorByUsername(registeredAuthor.getUsername());
        final var membershipRequest = (MembershipRequest) execution.getVariable(Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(membershipRequest.getId());
        if (authorOptional.isPresent() && membershipRequestOptional.isPresent()) {
            final var author = authorOptional.get();
            author.setIsVerified(false);
            author.setMembershipStatus(MembershipStatus.INACTIVE);

            this.membershipRequestRepository.delete(membershipRequestOptional.get());
            this.authorRepository.save(author);
        }

        log.info(LogMessages.FINISHED, CleanupMembershipRequestSubmission.class.getName());
    }
}

