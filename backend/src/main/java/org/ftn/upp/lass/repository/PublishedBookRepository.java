package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.PublishedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedBookRepository extends JpaRepository<PublishedBook, Long>, JpaSpecificationExecutor<PublishedBook> {

}
