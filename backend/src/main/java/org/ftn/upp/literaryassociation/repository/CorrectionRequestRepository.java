package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.CorrectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionRequestRepository extends JpaRepository<CorrectionRequest, Long>, JpaSpecificationExecutor<CorrectionRequest> {

}
