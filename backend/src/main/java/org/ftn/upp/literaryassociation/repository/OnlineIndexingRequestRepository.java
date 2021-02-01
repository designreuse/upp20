package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.OnlineIndexingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineIndexingRequestRepository extends JpaRepository<OnlineIndexingRequest, Long>, JpaSpecificationExecutor<OnlineIndexingRequest> {

}
