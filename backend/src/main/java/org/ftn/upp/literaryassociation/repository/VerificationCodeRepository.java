package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>, JpaSpecificationExecutor<VerificationCode> {

}
