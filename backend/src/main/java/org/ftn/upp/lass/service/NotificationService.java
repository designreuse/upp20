package org.ftn.upp.lass.service;

import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.User;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Notification delivery service.
 */
public interface NotificationService {

     @Async
     void sendVerificationEmail(User recipientUser, String processInstanceId) throws MessagingException;
     @Async
     void sendMembershipRequestEmail(List<BoardMember> recipientUsers, MembershipRequest membershipRequest, String processInstanceId) throws MessagingException;
     @Async
     void sendRequestForMoreDocumentsEmail(User recipientUser, String processInstanceId) throws MessagingException;
     @Async
     void sendResubmissionEmail(List<BoardMember> recipientUsers, MembershipRequest membershipRequest, String processInstanceId) throws MessagingException;
     @Async
     void sendMembershipRequestAcceptanceEmail(User recipientUser, String processInstanceId) throws MessagingException;
}
