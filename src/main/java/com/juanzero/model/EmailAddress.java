package com.juanzero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAddress {

    private String address;
    private String name;

    public EmailAddress(String address) {
        this.address = address;
        this.name = address.substring(0, address.indexOf('@'));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
