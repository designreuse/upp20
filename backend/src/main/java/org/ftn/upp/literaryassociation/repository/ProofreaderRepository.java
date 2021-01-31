package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.Proofreader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofreaderRepository extends JpaRepository<Proofreader, Long>, JpaSpecificationExecutor<Proofreader> {

}
