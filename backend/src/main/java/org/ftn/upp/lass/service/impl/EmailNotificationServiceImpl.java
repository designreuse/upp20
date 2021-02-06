package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.User;
import org.ftn.upp.lass.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

/**
 * E-mail notification delivery service implementation.
 */
@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements NotificationService {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${templates.html.verification}")
    private String verificationTemplateName;

    @Value("${templates.html.membership_request}")
    private String membershipRequestTemplateName;

    @Value("${templates.html.resubmission_required}")
    private String resubmissionRequiredTemplateName;

    @Value("${templates.html.resubmission_finished}")
    private String resubmissionFinishedTemplateName;

    @Value("${templates.html.membership_request_acceptance}")
    private String membershipRequestAcceptanceTemplateName;
    private final JavaMailSender mailSender;
    private final ITemplateEngine springTemplateEngine;

    /**
     * Sends a verification e-mail to the registered user.
     * In case an messaging error on the SMTP server occurs, a {@link MessagingException} is thrown.
     *
     * @param recipientUser {@link User} instance as recipient
     * @throws MessagingException Exception thrown in case an error on the SMTP server occurs
     */
    @Override
    @Async
    public void sendVerificationEmail(User recipientUser, String processInstanceId) throws MessagingException {
        this.sendEmail(
                recipientUser.getEmail(),
                "LASS Verification - Please confirm your account",
                this.generateVerificationMail(recipientUser, processInstanceId));
    }

    /**
     * Sends a membership request e-mail to given board members.
     * In case an messaging error on the SMTP server occurs, a {@link MessagingException} is thrown.
     *
     * @param recipientUsers {@link BoardMember} instances as recipients
     * @param membershipRequest {@link MembershipRequest} instance
     * @throws MessagingException Exception thrown in case an error on the SMTP server occurs
     */
    @Override
    @Async
    public void sendMembershipRequestEmail(List<BoardMember> recipientUsers, MembershipRequest membershipRequest, String processInstanceId) throws MessagingException {

        // TODO (fivkovic): Get documents from DB and add them to email

        for (User recipientUser : recipientUsers) {
            this.sendEmail(
                    recipientUser.getEmail(),
                    "LASS Membership Request - Review required",
                    this.generateMembershipRequestMail(recipientUser, membershipRequest, processInstanceId));
        }
    }

    @Override
    @Async
    public void sendRequestForMoreDocumentsEmail(User recipientUser, String processInstanceId) throws MessagingException {
        this.sendEmail(
                recipientUser.getEmail(),
                "LASS Membership Request - Resubmission required",
                this.generateResubmissionRequiredMail(recipientUser, processInstanceId));
    }

    @Override
    @Async
    public void sendResubmissionEmail(List<BoardMember> recipientUsers, MembershipRequest membershipRequest, String processInstanceId) throws MessagingException {

        // TODO (fivkovic): Get documents from DB and add them to email

        for (User recipientUser : recipientUsers) {
            this.sendEmail(
                    recipientUser.getEmail(),
                    "LASS Membership Request - Resubmission finished",
                    this.generateResubmissionFinishedMail(recipientUser, membershipRequest, processInstanceId));
        }
    }

    @Override
    @Async
    public void sendMembershipRequestAcceptanceEmail(User recipientUser, String processInstanceId) throws MessagingException {
        this.sendEmail(
                recipientUser.getEmail(),
                "LASS Membership Request - Accepted",
                this.generateMembershipRequestAcceptanceMail(recipientUser, processInstanceId));
    }

    /**
     * Sends an email with given content and attachments to the recipient.
     *
     * In case a messaging error on the SMTP server occurs, a {@link MessagingException} is thrown.
     * @param recipientEmailAddress Recipient's email address
     * @param subject               Email subject
     * @param text                  Email content
     * @param fileName              Name of the attachment file
     * @param pdf                   PDF file
     * @param html                  HTML file
     * @throws MessagingException Exception thrown in case an error on the SMTP server occurs
     */
    @Async
    void sendEmailWithAttachments(String recipientEmailAddress, String subject, String text,
                                  String fileName, byte[] pdf, String html) throws MessagingException {

        final String currentDate = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        final String newFileName = String.format("%s_%s", fileName, currentDate);

        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(text, TEXT_HTML_VALUE);

        MimeBodyPart pdfAttachmentBodyPart = new MimeBodyPart();
        pdfAttachmentBodyPart.setContent(pdf, APPLICATION_PDF_VALUE);
        pdfAttachmentBodyPart.setFileName(newFileName + ".pdf");

        MimeBodyPart htmlAttachmentBodyPart = new MimeBodyPart();
        htmlAttachmentBodyPart.setContent(html, TEXT_HTML_VALUE);
        htmlAttachmentBodyPart.setFileName(newFileName + ".html");

        Multipart multipartContent = new MimeMultipart();
        multipartContent.addBodyPart(textBodyPart);
        multipartContent.addBodyPart(pdfAttachmentBodyPart);
        multipartContent.addBodyPart(htmlAttachmentBodyPart);

        this.sendEmail(recipientEmailAddress, subject, multipartContent);
    }

    /**
     * Sends an email.
     * In case an messaging error on the SMTP server occurs, a {@link MessagingException} is thrown.
     *
     * @param recipientEmailAddress Recipient's email address
     * @param subject               Email subject
     * @param content               Email content
     * @throws MessagingException   Exception thrown in case an error on the SMTP server occurs
     */
    @Async
    void sendEmail(String recipientEmailAddress, String subject, String content) throws MessagingException {
        final MimeMessage mailMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, StandardCharsets.UTF_8.name());

        messageHelper.setFrom(this.email);
        messageHelper.setTo(recipientEmailAddress);
        messageHelper.setSubject(subject);
        mailMessage.setContent(content, TEXT_HTML_VALUE);

        this.mailSender.send(mailMessage);
    }

    /**
     * Sends an email with multipart content.
     * In case an messaging error on the SMTP server occurs, a {@link MessagingException} is thrown.
     *
     * @param recipientEmailAddress Recipient's email address
     * @param subject               Email subject
     * @param content               Email content
     * @throws MessagingException   Exception thrown in case an error on the SMTP server occurs
     */
    @Async
    void sendEmail(String recipientEmailAddress, String subject, Multipart content) throws MessagingException {
        final MimeMessage mailMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, StandardCharsets.UTF_8.name());

        messageHelper.setFrom(this.email);
        messageHelper.setTo(recipientEmailAddress);
        messageHelper.setSubject(subject);
        mailMessage.setContent(content);

        this.mailSender.send(mailMessage);
    }

    /**
     * Generates verification e-mail content using a template, based on the user's info.
     *
     * @param recipientUser {@link User} instance as recipient, used to fill template data
     * @param processInstanceId unique identifier of the process instance
     * @return Verification email HTML content
     */
    private String generateVerificationMail(User recipientUser, String processInstanceId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("recipient", recipientUser.getFirstName());
        variables.put("code", recipientUser.getVerificationCode().getValue());
        variables.put("pid", processInstanceId);

        return this.springTemplateEngine
                .process(this.verificationTemplateName, new Context(Locale.getDefault(), variables));
    }

    private String generateMembershipRequestMail(User recipientUser, MembershipRequest membershipRequest, String processInstanceId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("recipient", recipientUser.getFirstName());
        variables.put("author", membershipRequest.getAuthor().getFirstName().concat(" ").concat(membershipRequest.getAuthor().getLastName()));
        variables.put("coverLetter", membershipRequest.getCoverLetter());
        variables.put("pid", processInstanceId);

        return this.springTemplateEngine
                .process(this.membershipRequestTemplateName, new Context(Locale.getDefault(), variables));
    }

    private String generateResubmissionRequiredMail(User recipientUser, String processInstanceId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("recipient", recipientUser.getFirstName());
        variables.put("pid", processInstanceId);

        return this.springTemplateEngine
                .process(this.resubmissionRequiredTemplateName, new Context(Locale.getDefault(), variables));
    }

}
