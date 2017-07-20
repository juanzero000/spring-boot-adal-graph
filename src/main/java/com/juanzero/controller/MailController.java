package com.juanzero.controller;

import com.juanzero.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/sendMail")
    @ResponseStatus(HttpStatus.OK)
    public void sendMail(@RequestParam String mailRecipients, @RequestParam String subject, @RequestParam String content){
        mailService.sendMail(mailRecipients, subject, content);
    }
}
