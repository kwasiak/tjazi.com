package com.tjazi.webapp.controller;

import com.tjazi.webapp.messages.AuthenticateUserResponseMessage;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by kwasiak on 21/07/15.
 */
@RestController
@RequestMapping(value = "/security")
public class SecurityController {

    @RequestMapping(value = "/authenticateuser", method = RequestMethod.GET)
    public AuthenticateUserResponseMessage authenticateUser(
            @RequestHeader(value = "Chatroom") String chatroomName) {

        // all the security stuff is dealt by Spring in the background
        // all has to be done is sending token back to the requestor
        return new AuthenticateUserResponseMessage(UUID.randomUUID().toString());
    }
}
