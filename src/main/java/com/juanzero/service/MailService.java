package com.juanzero.service;

/**
 * Created by jjmendoza on 20/7/2017.
 */
public interface MailService {
    void sendMail(String mailRecipients, String subject, String content);
}
