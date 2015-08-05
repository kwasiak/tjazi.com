package com.tjazi.webapp.controller.chatroom;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by kwasiak on 26/07/15.
 */
@Controller
@RequestMapping(value = "/chatroom")
public class ChatroomJoinController {

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private SecurityService securityService;

    private static final Logger log = LoggerFactory.getLogger(ChatroomJoinController.class);

    @RequestMapping(value = "/join/{chatroomUuidStr}", method = RequestMethod.GET)
    public String joinChatroom(@PathVariable String chatroomUuidStr) {

        if (chatroomUuidStr == null || chatroomUuidStr.isEmpty()){
            log.error("chatoomUuidStr is null or empty. Navigating to /");
            return getHomeRedirectionUrl();
        }

        UUID chatroomUuid = this.parseChatroomUuid(chatroomUuidStr);

        if (chatroomUuid == null) {
            /* TODO: add error reporting at the client side */
            // cut down the bad UUID to 100 characters to avoid cases of
            // someone trying to push a lot of content into log file
            log.error("Problems with parsing entry chatroomUuidStr ('{}'). Navigating to /",
                    chatroomUuidStr.substring(0, chatroomUuidStr.length() < 100 ? chatroomUuidStr.length() : 100));
            return getHomeRedirectionUrl();
        }

        SingleChatroomDriver chatroomByUuid = chatroomService.findChatroomByUuid(chatroomUuid);

        if (chatroomByUuid == null) {
            /* TODO: add error reporting at the client side */
            log.error("Can't find Chatroom with given UUID ('{}'). Navigating to /", chatroomByUuid);
            return getHomeRedirectionUrl();
        }

        String currentUser = securityService.getCurrentUserName();

        if (chatroomByUuid.isUserInChatroom(currentUser)) {
            log.warn("User '{}' is already part of chatroom '{}' (UUID: {}).", currentUser, chatroomByUuid.getChatroomName(), chatroomUuid);
            return getChatroomRedirectionUrl(chatroomUuid);
        }

        chatroomByUuid.addUserToChatroom(currentUser);

        return getChatroomRedirectionUrl(chatroomUuid);
    }

    private UUID parseChatroomUuid(String chatroomUuid) {
        try
        {
            return UUID.fromString(chatroomUuid);
        }
        catch(IllegalArgumentException ex){

            // the UUID string is clearly broken
            return null;
        }
    }

    private String getHomeRedirectionUrl() {
        return "redirect:/";
    }

    private String getChatroomRedirectionUrl(UUID chatroomUuid) {
        return "redirect:/index#chatscreen/" + chatroomUuid;
    }

}
