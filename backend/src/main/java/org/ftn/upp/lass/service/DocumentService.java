package org.ftn.upp.lass.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    void storeDocuments(String processInstanceId, MultipartFile[] multipartFiles);
}
