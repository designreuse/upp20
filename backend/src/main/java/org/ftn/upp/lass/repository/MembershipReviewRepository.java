package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.MembershipReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipReviewRepository extends JpaRepository<MembershipReview, Long>, JpaSpecificationExecutor<MembershipReview> {
    Optional<MembershipReview> findMembershipReviewByRevieweeAndMembershipRequest(BoardMember reviewee, MembershipRequest membershipRequest);
}
