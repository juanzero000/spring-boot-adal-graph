package com.juanzero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {

    private String contentType;
    private String content;

    public Body(String contentType, String content) {
        this.contentType = contentType;
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
