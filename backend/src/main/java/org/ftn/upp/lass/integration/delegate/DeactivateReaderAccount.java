package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.MembershipStatus;
import org.ftn.upp.lass.model.Reader;
import org.ftn.upp.lass.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeactivateReaderAccount implements JavaDelegate {

    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, DeactivateReaderAccount.class.getName());

        final var username = (String) execution.getVariable(Constants.FormFieldVariables.USERNAME);
        final var userOptional = this.userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            final var reader = (Reader) userOptional.get();
            reader.setIsVerified(false);
            reader.setMembershipStatus(MembershipStatus.INACTIVE);

            this.userRepository.save(reader);
        }

        log.info(LogMessages.FINISHED, DeactivateReaderAccount.class.getName());
    }
}