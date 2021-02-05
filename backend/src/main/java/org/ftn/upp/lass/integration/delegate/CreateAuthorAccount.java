package org.ftn.upp.lass.integration.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.exception.NotFoundException;
import org.ftn.upp.lass.model.*;
import org.ftn.upp.lass.repository.*;
import org.ftn.upp.lass.service.CamundaIdentityService;
import org.ftn.upp.lass.util.FormSubmissionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateAuthorAccount implements JavaDelegate {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CamundaIdentityService camundaIdentityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(LogMessages.EXECUTE, CreateAuthorAccount.class.getName());

        final var userDataFormSubmissionFields= FormSubmissionUtils.extractFormSubmissionFields(execution.getVariable(Constants.FormDataVariables.USER_DATA_FORM));
        final var author= this.extractAuthor(userDataFormSubmissionFields);

        if (userRepository.existsUserByUsername(author.getUsername())) {
            throw new BpmnError("ERR_001", MessageFormat.format("Username {0} already taken.", author.getUsername()));
        }
        if (userRepository.existsUserByEmail(author.getEmail())) {
            throw new BpmnError("ERR_001", MessageFormat.format("Email {0} already taken.", author.getUsername()));
        }

        this.authorRepository.save(author);
        this.camundaIdentityService.addUser(author);
        execution.setVariable(Constants.ProcessVariables.REGISTERED_AUTHOR, author);

        log.info(LogMessages.FINISHED, CreateAuthorAccount.class.getName());
    }

    private Author extractAuthor(Map<String, Object> userDataFormSubmissionFields) {

        final var favoriteGenresIds = (List<String>) userDataFormSubmissionFields.get(Constants.FormFieldVariables.FAVORITE_GENRES);
        final var favoriteGenres = this.extractGenres(favoriteGenresIds);

        return Author.builder()
                .role(UserRole.AUTHOR)
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
                        .orElseThrow(() -> new NotFoundException(MessageFormat.format("Genre with ID {0} not found.", genreIdString)))
                ).collect(Collectors.toSet());
    }
}