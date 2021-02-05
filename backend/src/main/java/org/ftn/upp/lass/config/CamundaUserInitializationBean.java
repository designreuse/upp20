package org.ftn.upp.lass.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.repository.UserRepository;
import org.ftn.upp.lass.service.CamundaIdentityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
class CamundaUserInitializationBean implements InitializingBean {

    private final UserRepository userRepository;
    private final CamundaIdentityService camundaIdentityService;

    @Override
    public void afterPropertiesSet() {
        log.info(LogMessages.EXECUTE, CamundaUserInitializationBean.class.getName());

        final var allUsers = this.userRepository.findAll();
        allUsers.forEach(this.camundaIdentityService::deleteUser);
        allUsers.forEach(this.camundaIdentityService::addUser);

        log.info(LogMessages.FINISHED, CamundaUserInitializationBean.class.getName());
    }

    @PreDestroy
    public void onExit() {
        log.info(LogMessages.EXECUTE, CamundaUserInitializationBean.class.getName());

        final var allUsers = this.userRepository.findAll();
        allUsers.forEach(this.camundaIdentityService::deleteUser);

        log.info(LogMessages.FINISHED, CamundaUserInitializationBean.class.getName());
    }
}