package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.OnlineIndexingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineIndexingRequestRepository extends JpaRepository<OnlineIndexingRequest, Long>, JpaSpecificationExecutor<OnlineIndexingRequest> {

}
