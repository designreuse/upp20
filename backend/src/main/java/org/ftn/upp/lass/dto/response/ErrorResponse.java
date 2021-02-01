package org.ftn.upp.lass.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ftn.upp.lass.common.api.RestApiResponseParameters;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonProperty(RestApiResponseParameters.RESPONSE_CODE)
    private Integer responseCode;

    @JsonProperty(RestApiResponseParameters.MESSAGE)
    private String message;

    @JsonProperty(RestApiResponseParameters.BODY)
    private Map<String, String> body = new HashMap<>();

    public static ErrorResponse fromBadRequestException(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponseCode(e.getBadRequestResponseCode().getCode());
        errorResponse.setMessage(e.getBadRequestResponseCode().getMessage());
        errorResponse.getBody().putIfAbsent(RestApiResponseParameters.MESSAGE, e.getMessage());

        return errorResponse;
    }

    public static ErrorResponse fromNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getBody().putIfAbsent(RestApiResponseParameters.MESSAGE, e.getMessage());

        return errorResponse;
    }

    public static ErrorResponse fromInsufficientPrivilegesException() {
        return ErrorResponse.builder()
                .responseCode(BadRequestResponseCode.INSUFFICIENT_PRIVILEGES.getCode())
                .message(BadRequestResponseCode.INSUFFICIENT_PRIVILEGES.getMessage())
                .build();
    }

    public static ErrorResponse fromGeneralException() {
        return ErrorResponse.builder()
                .responseCode(BadRequestResponseCode.INTERNAL_SERVER_ERROR.getCode())
                .message(BadRequestResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
