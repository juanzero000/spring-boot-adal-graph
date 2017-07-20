package com.juanzero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipient {

    private EmailAddress emailAddress;

    public Recipient(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
}
