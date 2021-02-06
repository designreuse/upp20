package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.Document;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.repository.DocumentRepository;
import org.ftn.upp.lass.repository.MembershipRequestRepository;
import org.ftn.upp.lass.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final MembershipRequestRepository membershipRequestRepository;
    private final DocumentRepository documentRepository;
    private final RuntimeService runtimeService;

    @Override
    public void storeDocuments(String processInstanceId, MultipartFile[] multipartFiles) {
        log.info(LogMessages.EXECUTE, "storeDocuments");

        final var createdMembershipRequest = (MembershipRequest) this.runtimeService.getVariable(processInstanceId, Constants.ProcessVariables.CREATED_MEMBERSHIP_REQUEST);
        final var membershipRequestOptional = this.membershipRequestRepository.findById(createdMembershipRequest.getId());
        if (membershipRequestOptional.isPresent()) {
            final var membershipRequest = membershipRequestOptional.get();

            Stream.of(multipartFiles).forEach(multipartFile -> {
                byte[] byteContent = new byte[0];
                try {
                    byteContent = multipartFile.getBytes();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }

                final var document = Document.builder()
                        .name(multipartFile.getOriginalFilename())
                        .contentType(multipartFile.getContentType())
                        .byteContent(byteContent)
                        .build();
                this.documentRepository.save(document);
                membershipRequest.getSubmittedDocuments().add(document);
            });

            this.membershipRequestRepository.save(membershipRequest);
        }

        log.info(LogMessages.FINISHED, "storeDocuments");
    }
}
