package org.ftn.upp.lass.integration.validator;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;
import org.ftn.upp.lass.config.SpringApplicationContext;
import org.ftn.upp.lass.repository.UserRepository;

public class UsernameAvailabilityValidator implements FormFieldValidator {

    private final UserRepository userRepository;

    public UsernameAvailabilityValidator() {
        this.userRepository = SpringApplicationContext.getBean(UserRepository.class);
    }

    @Override
    public boolean validate(Object submittedValue, FormFieldValidatorContext validatorContext) {
        String username = (String) submittedValue;
        return !this.userRepository.existsUserByUsername(username);
    }
}