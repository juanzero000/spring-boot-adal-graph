package com.juanzero.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.juanzero.model.EmailAddress;
import com.juanzero.model.MessageRequest;
import com.juanzero.model.Recipient;
import com.juanzero.service.MailService;
import com.juanzero.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by jjmendoza on 20/7/2017.
 */
@Service
public class MailServiceImpl implements MailService {

    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${sb-adal-graph.user}")
    private String user;

    @Value("${sb-adal-graph.password}")
    private String password;

    @Value("${sb-adal-graph.sendMailEndpoint}")
    private String sendMailEndpoint;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendMail(String mailRecipients, String subject, String content) {
        try {
            MessageRequest messageRequest = buildMessageRequest(mailRecipients, subject, content);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String accessToken = tokenUtils.getAccessTokenFromUserCredentials(user, password);
            headers.add("Authorization", "Bearer " + accessToken);

            HttpEntity<MessageRequest> request = new HttpEntity<>(messageRequest, headers);

            Gson gsonBuilder = new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                    .create();
            logger.info("\n\nsending:\n\n" + gsonBuilder.toJson(messageRequest));

            restTemplate.postForObject(sendMailEndpoint, request, MessageRequest.class);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
        }
    }

    private MessageRequest buildMessageRequest(String mailRecipients, String subject, String content) {
        MessageRequest messageRequest = new MessageRequest(subject, content);
        messageRequest.getMessage().setToRecipients(buildRecipients(mailRecipients));
        return messageRequest;
    }

    private Recipient[] buildRecipients(String mailRecipients) {
        if (mailRecipients != null) {
            return Arrays.stream(mailRecipients.split(","))
                    .map(String::trim)
                    .map(EmailAddress::new)
                    .map(Recipient::new)
                    .toArray(Recipient[]::new);
        }
        return new Recipient[]{};
    }
}
