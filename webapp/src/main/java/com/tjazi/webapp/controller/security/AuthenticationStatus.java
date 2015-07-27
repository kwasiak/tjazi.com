package com.tjazi.webapp.controller.security;

import com.tjazi.security.service.SecurityService;
import com.tjazi.session.service.SessionService;
import com.tjazi.webapp.messages.security.AuthenticateUserResponseMessage;
import com.tjazi.webapp.messages.security.IsUserAuthenticatedResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 27/07/15.
 */

@RestController
@RequestMapping(value = "/security")
public class AuthenticationStatus {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionService sessionService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationStatus.class);

    /**
     * Check if current user (as per session) is authenticated.
     * There're no parameters here - it will be checked based on the current session data.
     * @return Authentication details for the given user
     */

    @RequestMapping(value = "/isuserauthenticated", method = RequestMethod.GET)
    public IsUserAuthenticatedResponseMessage isUserAuthenticated() {

        String currentUser = securityService.getCurrentUserName();

        if (currentUser == null) {

            log.debug("User for current session is not authenticated.");
            return new IsUserAuthenticatedResponseMessage(false, null);
        }

        log.debug("User for current session is authenticated as: " + currentUser);

        String authenticationToken = sessionService.getAuthenticationToken();

        if (authenticationToken == null && authenticationToken.isEmpty()) {
            log.error("Authentication for user '{}' doesn't have corresponding authentication token.");
            return new IsUserAuthenticatedResponseMessage(false, null);
        }

        return new IsUserAuthenticatedResponseMessage(true, authenticationToken);
    }
}
