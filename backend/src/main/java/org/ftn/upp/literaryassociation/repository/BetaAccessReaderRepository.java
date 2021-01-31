package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.BetaAccessReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BetaAccessReaderRepository extends JpaRepository<BetaAccessReader, Long>, JpaSpecificationExecutor<BetaAccessReader> {

}
