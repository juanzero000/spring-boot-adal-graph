package com.juanzero.util;

import com.juanzero.service.impl.MailServiceImpl;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jjmendoza on 20/7/2017.
 */
public class TokenUtils {
    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${sb-adal-graph.authority}")
    private String authority;

    @Value("${sb-adal-graph.clientId}")
    private String clientId;

    @Value("${sb-adal-graph.resource}")
    private String resource;

    public String getAccessTokenFromUserCredentials(String user, String password) {
        AuthenticationContext context = null;
        AuthenticationResult result = null;
        ExecutorService service = null;

        try {

            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(authority, true, service);

            Future<AuthenticationResult> future = context.acquireToken(
                    resource, clientId, user, password, null);
            result = future.get();

        } catch (InterruptedException | ExecutionException | MalformedURLException e) {
            logger.error(e.getMessage());
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new RuntimeException(
                    "authentication result was null");
        }

        return result.getAccessToken();
    }
}