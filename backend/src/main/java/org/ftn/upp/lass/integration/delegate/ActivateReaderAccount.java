package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivateReaderAccount implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("EXECUTE ActivateReaderAccount");

        // TODO: Implement
    }
}