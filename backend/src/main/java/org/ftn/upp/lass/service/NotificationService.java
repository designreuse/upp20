package org.ftn.upp.lass.service;

import org.ftn.upp.lass.model.User;

import javax.mail.MessagingException;

/**
 * Notification delivery service.
 */
public interface NotificationService {

     void sendVerificationEmail(User recipientUser, String processInstanceId) throws MessagingException;
}
