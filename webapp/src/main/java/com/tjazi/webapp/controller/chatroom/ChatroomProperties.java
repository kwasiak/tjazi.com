package com.tjazi.webapp.controller.chatroom;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.webapp.messages.GetChatroomPropertiesMessage;
import com.tjazi.webapp.messages.GetChatroomPropertiesResponseMessage;
import com.tjazi.webapp.messages.GetChatroomPropertiesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by kwasiak on 26/07/15.
 */
@RestController
@RequestMapping(value = "/chatroom")
public class ChatroomProperties {

    @Autowired
    private ChatroomService chatroomService;

    private static final Logger log = LoggerFactory.getLogger(ChatroomProperties.class);

    @RequestMapping(value = "/properties", method = RequestMethod.POST)
    public GetChatroomPropertiesResponseMessage getChatroomProperties(
            @RequestBody GetChatroomPropertiesMessage getChatroomPropertiesMessage){

        if (getChatroomPropertiesMessage == null) {
            log.error("Got null getChatroomPropertiesMessage.");

            return new GetChatroomPropertiesResponseMessage(GetChatroomPropertiesResult.WRONG_PARAMETERS, null, null);
        }

        UUID chatroomUuid = getChatroomPropertiesMessage.getChatroomUuid();

        if (chatroomUuid == null) {
            log.error("Got null Chatroom UUID in getChatroomPropertiesMessage");
            return new GetChatroomPropertiesResponseMessage(GetChatroomPropertiesResult.WRONG_PARAMETERS, null, null);
        }

        final SingleChatroomDriver singleChatroomDriver = chatroomService.findChatroomByUuid(chatroomUuid);

        if (singleChatroomDriver == null) {
            log.error("Can't find chatroom with given GUID: " + chatroomUuid);
        }

        // let's check user permissions
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            log.error("Not authenticated user attempted to download properties of chatroom '{}' (GUID: {})",
                    singleChatroomDriver.getChatroomName(), singleChatroomDriver.getChatroomUuid());
            return new GetChatroomPropertiesResponseMessage(
                    GetChatroomPropertiesResult.PERMISSION_DENIED, null, null);
        }

        String authenticatedUserName = auth.getName();

        if (!singleChatroomDriver.isUserInChatroom(authenticatedUserName)) {
            log.error("User '{}' is authenticated, but doesn't belong to chatroom '{}' (GUID: {}). Permission denied.",
                    singleChatroomDriver.getChatroomName(), singleChatroomDriver.getChatroomUuid());
            return new GetChatroomPropertiesResponseMessage(
                    GetChatroomPropertiesResult.PERMISSION_DENIED, null, null);
        }

        // all fine - return the chatroom data!!!
        return new GetChatroomPropertiesResponseMessage(
                GetChatroomPropertiesResult.OK,
                singleChatroomDriver.getChatroomName(),
                singleChatroomDriver.getChatroomUsers()
        );
    }
}