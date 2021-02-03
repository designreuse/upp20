package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.model.*;
import org.ftn.upp.lass.repository.BetaAccessReaderRepository;
import org.ftn.upp.lass.repository.GenreRepository;
import org.ftn.upp.lass.repository.ReaderRepository;
import org.ftn.upp.lass.repository.UserRepository;
import org.ftn.upp.lass.service.CamundaIdentityService;
import org.ftn.upp.lass.util.FormSubmissionUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateReaderAccount implements JavaDelegate {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BetaAccessReaderRepository betaAccessReaderRepository;
    private final ReaderRepository readerRepository;
    private final GenreRepository genreRepository;
    private final CamundaIdentityService camundaIdentityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, CreateReaderAccount.class.getName());

        final var userDataFormSubmissionFields= FormSubmissionUtils.extractFormSubmissionFields(execution.getVariable(Constants.FormDataVariables.USER_DATA_FORM));
        final var isBetaAccessRequested= Boolean.parseBoolean((String)userDataFormSubmissionFields.get(Constants.FormFieldVariables.IS_BETA_ACCESS_REQUESTED));
        if (isBetaAccessRequested) {
            final var betaAccessGenresFormSubmissionFields= FormSubmissionUtils.extractFormSubmissionFields(execution.getVariable(Constants.FormDataVariables.BETA_ACCESS_GENRES_FORM));
            var betaAccessReader= this.extractBetaAccessReader(userDataFormSubmissionFields, betaAccessGenresFormSubmissionFields);

            if (userRepository.existsUserByUsername(betaAccessReader.getUsername())) {
                throw new BpmnError("ERR_001", "Username already taken.");
            }
            if (userRepository.existsUserByEmail(betaAccessReader.getEmail())) {
                throw new BpmnError("ERR_001", "Email already taken.");
            }

            this.betaAccessReaderRepository.save(betaAccessReader);
            this.camundaIdentityService.addUser(betaAccessReader);
            execution.setVariable(Constants.ProcessVariables.REGISTERED_READER, betaAccessReader);
        } else {
            var reader= this.extractReader(userDataFormSubmissionFields);

            if (userRepository.existsUserByUsername(reader.getUsername())) {
                throw new BpmnError("ERR_001", "Username already taken.");
            }
            if (userRepository.existsUserByEmail(reader.getEmail())) {
                throw new BpmnError("ERR_001", "Email already taken.");
            }

            this.readerRepository.save(reader);
            this.camundaIdentityService.addUser(reader);
            execution.setVariable(Constants.ProcessVariables.REGISTERED_READER, reader);
        }

        log.info(LogMessages.FINISHED, CreateReaderAccount.class.getName());
    }

    private BetaAccessReader extractBetaAccessReader(Map<String, Object> userDataFormSubmissionFields, Map<String, Object> betaAccessGenresFormSubmissionFields) {

        final var favoriteGenresIds = (List<String>) userDataFormSubmissionFields.get(Constants.FormFieldVariables.FAVORITE_GENRES);
        final var favoriteGenres = this.extractGenres(favoriteGenresIds);
        final var betaAccessGenresIds = (List<String>) betaAccessGenresFormSubmissionFields.get(Constants.FormFieldVariables.BETA_ACCESS_GENRES);
        final var betaAccessGenres = this.extractGenres(betaAccessGenresIds);

        return BetaAccessReader.builder()
                .role(UserRole.BETA_ACCESS_READER)
                .firstName((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.FIRST_NAME))
                .lastName((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LAST_NAME))
                .username((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.USERNAME))
                .email((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.EMAIL))
                .password(this.passwordEncoder.encode((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.PASSWORD)))
                .address(Address.builder()
                        .street((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.STREET))
                        .city((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.CITY))
                        .postalCode((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.POSTAL_CODE))
                        .country((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.COUNTRY))
                        .latitude(Double.parseDouble((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LATITUDE)))
                        .longitude(Double.parseDouble((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LONGITUDE)))
                        .build())
                .verificationCode(VerificationCode.builder()
                        .value(UUID.randomUUID().toString())
                        .generatedAt(LocalDateTime.now())
                        .status(VerificationCodeStatus.NEW)
                        .build())
                .hasRequestedBetaAccess(true)
                .favoriteGenres(favoriteGenres)
                .membershipStatus(MembershipStatus.VERIFICATION_PENDING)
                .betaAccessGenres(betaAccessGenres)
                .build();
    }

    private Reader extractReader(Map<String, Object> userDataFormSubmissionFields) {

        final var favoriteGenresIds = (List<String>) userDataFormSubmissionFields.get(Constants.FormFieldVariables.FAVORITE_GENRES);
        final var favoriteGenres = this.extractGenres(favoriteGenresIds);

        return Reader.builder()
                .role(UserRole.READER)
                .firstName((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.FIRST_NAME))
                .lastName((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LAST_NAME))
                .username((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.USERNAME))
                .email((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.EMAIL))
                .password(this.passwordEncoder.encode((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.PASSWORD)))
                .address(Address.builder()
                        .street((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.STREET))
                        .city((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.CITY))
                        .postalCode((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.POSTAL_CODE))
                        .country((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.COUNTRY))
                        .latitude(Double.parseDouble((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LATITUDE)))
                        .longitude(Double.parseDouble((String) userDataFormSubmissionFields.get(Constants.FormFieldVariables.LONGITUDE)))
                        .build())
                .verificationCode(VerificationCode.builder()
                        .value(UUID.randomUUID().toString())
                        .generatedAt(LocalDateTime.now())
                        .status(VerificationCodeStatus.NEW)
                        .build())
                .favoriteGenres(favoriteGenres)
                .membershipStatus(MembershipStatus.VERIFICATION_PENDING)
                .build();
    }

    private Set<Genre> extractGenres(List<String> genreIdsStrings) {
        return genreIdsStrings.stream()
                .map(genreIdString -> this.genreRepository.findById(Long.valueOf(genreIdString))
                        .orElseThrow(() -> new ResourceNotFoundException("Genre not found."))
                ).collect(Collectors.toSet());
    }
}