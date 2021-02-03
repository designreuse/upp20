package org.ftn.upp.lass.util;

import org.ftn.upp.lass.dto.request.FormSubmissionField;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FormSubmissionUtils {

    private FormSubmissionUtils() { }

    public static Map<String, Object> extractFormSubmissionFields(@NotNull Object data) {
        return convertFormSubmissionFields((List<FormSubmissionField>) data);
    }

    public static Map<String, Object> convertFormSubmissionFields(@NotNull List<FormSubmissionField> formSubmissionFields) {
        return formSubmissionFields.stream().collect(Collectors.toMap(FormSubmissionField::getId, FormSubmissionField::getValue));
    }
}
