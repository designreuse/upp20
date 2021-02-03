package org.ftn.upp.lass.integration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.service.GenreService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadAvailableGenres implements JavaDelegate {

    private final GenreService genreService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info(LogMessages.LOADING_AVAILABLE_GENRES);
        var availableGenres = this.genreService.getAvailableGenres();
        delegateExecution.setVariable(Constants.ProcessVariables.AVAILABLE_GENRES, availableGenres);
        log.info(LogMessages.STORED_AVAILABLE_GENRES, Constants.ProcessVariables.AVAILABLE_GENRES, delegateExecution.getProcessInstanceId());
    }
}