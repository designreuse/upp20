package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.PlagiarismReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlagiarismReportRepository extends JpaRepository<PlagiarismReport, Long>, JpaSpecificationExecutor<PlagiarismReport> {

}
