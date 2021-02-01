package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.MembershipReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipReviewRepository extends JpaRepository<MembershipReview, Long>, JpaSpecificationExecutor<MembershipReview> {

}
