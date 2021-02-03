package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.model.VerificationCodeStatus;
import org.ftn.upp.lass.repository.VerificationCodeRepository;
import org.ftn.upp.lass.service.VerificationCodeService;
import org.ftn.upp.lass.util.ErrorMessageUtil;
import org.ftn.upp.lass.util.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public void markVerificationCodeAsUsed(String verificationCodeValue) {
        this.updateVerificationCodeStatus(verificationCodeValue, VerificationCodeStatus.USED);
    }

    @Override
    public void markVerificationCodeAsExpired(String verificationCodeValue) {
        this.updateVerificationCodeStatus(verificationCodeValue, VerificationCodeStatus.EXPIRED);
    }

    private void updateVerificationCodeStatus(String verificationCodeValue, VerificationCodeStatus verificationCodeStatus) {
        final var verificationCodeOptional = this.verificationCodeRepository.findVerificationCodeByValue(verificationCodeValue);
        if (verificationCodeOptional.isPresent()) {
            final var verificationCode = verificationCodeOptional.get();
            ExceptionUtils.throwBadRequestExceptionIf(verificationCode.isUsed(), BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.verificationCodeAlreadyUsed(verificationCodeValue));
            ExceptionUtils.throwBadRequestExceptionIf(verificationCode.hasExpired(), BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.verificationCodeExpired(verificationCodeValue));

            verificationCode.setStatus(verificationCodeStatus);
            this.verificationCodeRepository.save(verificationCode);
        } else {
            throw new BadRequestException(BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.verificationCodeDoesNotExist(verificationCodeValue));
        }
    }
}
