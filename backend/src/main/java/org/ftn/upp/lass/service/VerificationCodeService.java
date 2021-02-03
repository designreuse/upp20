package org.ftn.upp.lass.service;

public interface VerificationCodeService {

    void markVerificationCodeAsUsed(String verificationCodeValue);
    void markVerificationCodeAsExpired(String verificationCodeValue);
}
