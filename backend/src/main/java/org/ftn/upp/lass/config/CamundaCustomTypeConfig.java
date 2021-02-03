package org.ftn.upp.lass.config;

import org.camunda.bpm.engine.impl.form.type.AbstractFormFieldType;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.ftn.upp.lass.integration.custom.CustomSelectFormFieldType;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CamundaCustomTypeConfig extends AbstractCamundaConfiguration {

    public void preInit(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        if (springProcessEngineConfiguration.getCustomFormTypes() == null) {
            springProcessEngineConfiguration.setCustomFormTypes(new ArrayList<>());
        }

        springProcessEngineConfiguration.getCustomFormTypes().add(new CustomSelectFormFieldType());
    }
}