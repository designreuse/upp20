package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.exception.InternalServerException;
import org.ftn.upp.lass.integration.custom.CustomSelectFormFieldType;
import org.ftn.upp.lass.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PopulateFormWithGenres implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info(LogMessages.POPULATING_FORM_WITH_GENRES, delegateTask.getId());
        final var availableGenres = (List<Genre>) delegateTask.getVariable(Constants.ProcessVariables.AVAILABLE_GENRES);
        final var genresCustomFormField = delegateTask
                .getExecution()
                .getProcessEngineServices()
                .getFormService()
                .getTaskFormData(delegateTask.getId())
                .getFormFields()
                .stream()
                .filter(formField -> formField.getId().equals(Constants.ProcessVariables.FAVORITE_GENRES) || formField.getId().equals(Constants.ProcessVariables.BETA_ACCESS_GENRES))
                .findFirst();

        if (genresCustomFormField.isPresent()) {
            final var genresCustomFormFieldType = (CustomSelectFormFieldType) genresCustomFormField.get().getType();
            availableGenres.forEach(availableGenre -> genresCustomFormFieldType.getValues().put(availableGenre.getId().toString(), availableGenre.getName()));

            log.info(LogMessages.POPULATED_FORM_WITH_GENRES, delegateTask.getId());
        }
        else {
            throw new InternalServerException();
        }
    }
}

