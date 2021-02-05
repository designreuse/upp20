package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.dto.request.FormSubmissionField;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SetSubmittedDocumentsCount implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, SetSubmittedDocumentsCount.class.getName());

        final var formSubmissionFields = (List<FormSubmissionField>) execution.getVariable(Constants.FormDataVariables.DOCUMENTS_FORM);
        final var submittedDocumentFileNames = (List<String>) formSubmissionFields.stream().findFirst().get().getValue();

        execution.setVariable(Constants.ProcessVariables.SUBMITTED_DOCUMENTS_COUNT, submittedDocumentFileNames.size());

        log.info(LogMessages.FINISHED, SetSubmittedDocumentsCount.class.getName());
    }
}