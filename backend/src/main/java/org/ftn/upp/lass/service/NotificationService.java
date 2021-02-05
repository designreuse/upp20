package org.ftn.upp.lass.service;

import org.ftn.upp.lass.model.BoardMember;
import org.ftn.upp.lass.model.MembershipRequest;
import org.ftn.upp.lass.model.User;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Notification delivery service.
 */
public interface NotificationService {

     void sendVerificationEmail(User recipientUser, String processInstanceId) throws MessagingException;
     void sendMembershipRequestEmail(List<BoardMember> recipientUsers, MembershipRequest membershipRequest, String processInstanceId) throws MessagingException;
}
