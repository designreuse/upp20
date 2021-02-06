package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.service.DocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping(RestApiEndpoints.DOCUMENT)
@RequiredArgsConstructor
@Slf4j
public class DocumentUploadController {

    @Value("${allowed-content-types}")
    private String[] allowedContentTypes;

    private final DocumentService documentService;

    @PostMapping(consumes = {"application/octet-stream", "multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    public void uploadDocument(@RequestParam(RestApiRequestParameters.PROCESS_INSTANCE_ID) @NotBlank String processInstanceId,
                               @RequestPart(RestApiRequestParameters.DOCUMENTS) MultipartFile[] documents) {
        log.info(LogMessages.HANDLING_DOCUMENT_UPLOAD, processInstanceId);
        Stream.of(documents).forEach(document -> {
            if (!Arrays.asList(allowedContentTypes).contains(document.getContentType()))
                throw new BadRequestException(BadRequestResponseCode.INVALID_MEDIA_TYPE, MessageFormat.format("Invalid format of file {}.", document.getOriginalFilename()));
        });
        this.documentService.storeDocuments(processInstanceId, documents);
        log.info(LogMessages.HANDLED_DOCUMENT_UPLOAD, processInstanceId);
    }
}
