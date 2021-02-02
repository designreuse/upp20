package org.ftn.upp.lass.integration.validator;

import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorContext;

import java.util.List;

public class GenreSelectionValidator implements FormFieldValidator {

    @Override
    public boolean validate(Object submittedValue, FormFieldValidatorContext validatorContext) {
        var submittedGenres = (List<?>) submittedValue;
        return submittedGenres != null && !submittedGenres.isEmpty();
    }
}
