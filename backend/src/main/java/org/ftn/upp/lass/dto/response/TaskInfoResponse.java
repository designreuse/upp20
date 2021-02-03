package org.ftn.upp.lass.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskInfoResponse {

    private final String processInstanceId;
    private final String taskId;
}