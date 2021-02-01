package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.PlagiarismAssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlagiarismAssessmentResultRepository extends JpaRepository<PlagiarismAssessmentResult, Long>, JpaSpecificationExecutor<PlagiarismAssessmentResult> {

}
