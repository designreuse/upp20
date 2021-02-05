package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long>, JpaSpecificationExecutor<BoardMember> {

    List<BoardMember> findBoardMembersByUsernameIn(List<String> boardMemberUsernames);
}

