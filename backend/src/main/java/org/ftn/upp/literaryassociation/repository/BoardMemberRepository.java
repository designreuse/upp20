package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long>, JpaSpecificationExecutor<BoardMember> {

}

