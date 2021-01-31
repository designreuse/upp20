package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.SubmittedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedBookRepository extends JpaRepository<SubmittedBook, Long>, JpaSpecificationExecutor<SubmittedBook> {

}
