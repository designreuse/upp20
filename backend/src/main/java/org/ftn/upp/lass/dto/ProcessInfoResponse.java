package org.ftn.upp.lass.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessInfoResponse {

    private final String processInstanceId;
}
