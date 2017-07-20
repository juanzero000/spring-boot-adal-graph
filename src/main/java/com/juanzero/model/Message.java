package com.juanzero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private String subject;
    private Body body;
    private Recipient[] toRecipients;

    public Message(String subject, Body body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Recipient[] getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(Recipient[] toRecipients) {
        this.toRecipients = toRecipients;
    }
}
