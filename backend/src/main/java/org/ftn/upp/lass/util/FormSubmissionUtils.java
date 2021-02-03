package org.ftn.upp.lass.util;

import org.ftn.upp.lass.dto.request.FormSubmissionField;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FormSubmissionUtils {

    private FormSubmissionUtils() { }

    public static Map<String, Object> extractFormSubmissionFields(Object data) {
        return ((List<FormSubmissionField>) data).stream().collect(Collectors.toMap(FormSubmissionField::getId, FormSubmissionField::getValue));
    }
}
