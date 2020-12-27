package ftn.upp.literary.association.util;

import ftn.upp.literary.association.model.dto.FormSubmissionDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FormUtil {

    public static Map<String, Object> convertFormSubmissionsToMap(List<FormSubmissionDto> formSubmissions) {
        return formSubmissions.stream()
                .collect(Collectors.toMap(FormSubmissionDto::getFieldId, FormSubmissionDto::getFieldValue)
        );
    }
}
