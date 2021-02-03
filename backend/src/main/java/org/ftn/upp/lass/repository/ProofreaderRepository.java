package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.Proofreader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofreaderRepository extends JpaRepository<Proofreader, Long>, JpaSpecificationExecutor<Proofreader> {

}
