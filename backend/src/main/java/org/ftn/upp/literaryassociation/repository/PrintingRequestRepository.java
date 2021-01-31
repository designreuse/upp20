package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.PrintingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintingRequestRepository extends JpaRepository<PrintingRequest, Long>, JpaSpecificationExecutor<PrintingRequest> {

}
