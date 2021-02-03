package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>, JpaSpecificationExecutor<VerificationCode> {
    Optional<VerificationCode> findVerificationCodeByValue(String value);
}
