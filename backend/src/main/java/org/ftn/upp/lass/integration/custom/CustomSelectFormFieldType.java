package org.ftn.upp.lass.integration.custom;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSelectFormFieldType extends EnumFormType {

    private static final String TYPE_NAME = "CustomSelectFormField";

    public CustomSelectFormFieldType(Map<String, String> values) {
        super(values);
    }

    public CustomSelectFormFieldType() {
        super(new HashMap<>());
    }

    @Override
    public String getName() {
        return TYPE_NAME;
    }

    @Override
    public TypedValue convertValue(TypedValue propertyValue) {
        Object value = propertyValue.getValue();

        return value == null ? Variables.stringValue(null, propertyValue.isTransient()) : Variables.stringValue(value.toString(), propertyValue.isTransient());
    }

    @Override
    public Object convertFormValueToModelValue(Object propertyValue) {
        return propertyValue;
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        if (modelValue == null) throw new ProcessEngineException("Model value must not be null.");
        if (!(modelValue instanceof List)) throw new ProcessEngineException("Model value must be a List.");

        return modelValue.toString();
    }
}
