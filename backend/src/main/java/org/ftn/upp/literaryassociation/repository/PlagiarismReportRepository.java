package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.PlagiarismReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlagiarismReportRepository extends JpaRepository<PlagiarismReport, Long>, JpaSpecificationExecutor<PlagiarismReport> {

}
