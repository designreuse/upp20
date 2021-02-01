package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.SubmittedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedBookRepository extends JpaRepository<SubmittedBook, Long>, JpaSpecificationExecutor<SubmittedBook> {

}
