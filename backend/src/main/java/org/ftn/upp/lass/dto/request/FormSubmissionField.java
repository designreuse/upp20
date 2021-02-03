package org.ftn.upp.lass.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSubmissionField implements Serializable {

    private String id;
    private Object value;
}