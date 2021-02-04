package org.ftn.upp.lass.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.repository.UserRepository;
import org.ftn.upp.lass.service.CamundaIdentityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
class CamundaUserInitializationBean implements InitializingBean {

    private final UserRepository userRepository;
    private final CamundaIdentityService camundaIdentityService;

    @Override
    public void afterPropertiesSet() {
        log.info("EXECUTING CamundaUserInitializationBean");

        this.userRepository.findAll().forEach(this.camundaIdentityService::addUser);
    }
}