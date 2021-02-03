package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.User;
import org.ftn.upp.lass.service.CamundaIdentityService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class CamundaIdentityServiceImpl implements CamundaIdentityService {

    private final IdentityService identityService;

    @Override
    public void addUser(User user) {
        log.info(LogMessages.ADDING_USER_TO_CAMUNDA, user.getUsername());

        if (this.identityService.createUserQuery().userId(user.getUsername()).singleResult() != null) {
            throw new IllegalArgumentException(MessageFormat.format("Camunda Engine already has user with username {0}.", user.getUsername()));
        }
        final var camundaUser = this.identityService.newUser(user.getUsername());
        camundaUser.setFirstName(user.getFirstName());
        camundaUser.setLastName(user.getLastName());
        camundaUser.setEmail(user.getEmail());
        camundaUser.setPassword(user.getPassword());

        this.identityService.saveUser(camundaUser);
        log.info(LogMessages.ADDED_USER_TO_CAMUNDA, user.getUsername());
    }
}
