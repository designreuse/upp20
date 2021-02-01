package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.MembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequest, Long>, JpaSpecificationExecutor<MembershipRequest> {

}
