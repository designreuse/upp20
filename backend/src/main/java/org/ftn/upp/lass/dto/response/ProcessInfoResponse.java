package org.ftn.upp.lass.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessInfoResponse {

    private final String processInstanceId;
}
