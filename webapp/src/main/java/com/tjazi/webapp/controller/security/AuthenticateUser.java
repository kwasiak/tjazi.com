package com.tjazi.webapp.controller.security;

import com.tjazi.session.service.SessionService;
import com.tjazi.webapp.messages.security.AuthenticateUserResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by kwasiak on 27/07/15.
 */
@RestController
@RequestMapping(value = "/security")
public class AuthenticateUser {

    @Autowired
    private SessionService sessionService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticateUser.class);

    /**
     * Doesn't require user name & password - those are sent via Basic/Form authentication
     * All other things (session, etc.) are handled in backed by Spring MVC / Boot
     * @return Authentication token
     */
    @RequestMapping(value = "/authenticateuser", method = RequestMethod.GET)
    public AuthenticateUserResponseMessage authenticateUser() {

        String authenticationToken = generateAuthenticationToken();

        log.debug("Got authentication request. Generated token: " + authenticationToken);

        this.saveTokenToSession(authenticationToken);

        // all the security stuff is dealt by Spring in the background
        // all has to be done is sending token back to the requestor
        return new AuthenticateUserResponseMessage(authenticationToken);
    }

    private String generateAuthenticationToken() {
        return UUID.randomUUID().toString();
    }

    private void saveTokenToSession(String authenticationToken) {

        // check if there's already token saved for this session

        String existingAuthenticationToken = sessionService.getAuthenticationToken();
        if (existingAuthenticationToken != null) {
            log.error("There's already authentication token ({}) saved for this session. Overwriting...",
                    existingAuthenticationToken);
        }

        sessionService.setAuthenticationToken(authenticationToken);
    }
}
