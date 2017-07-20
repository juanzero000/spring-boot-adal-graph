package com.juanzero.model;

/**
 * Created by jjmendoza on 20/7/2017.
 */
public class MessageRequest {
    private Message message;

    public MessageRequest(String subject, String content){
        this.message = new Message(subject, new Body("Text",content));
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
