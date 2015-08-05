package com.tjazi.webapp.controller.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ChatroomJoinController.class);

    @RequestMapping(value = "/join/{chatroomUuidStr}", method = RequestMethod.GET)
    public String joinChatroom(@PathVariable String chatoomUuidStr) {

        if (chatoomUuidStr == null || chatoomUuidStr.isEmpty()){
            log.error("chatoomUuidStr is null or empty. Navigating to /");
            return "redirect:/";
        }

        UUID chatroomUuid = this.parseChatroomUuid(chatoomUuidStr);

        if (chatroomUuid == null) {
            log.error("Problems with parsing entry chatroomUuidStr ('{}'). Navigating to /",
                    chatoomUuidStr.substring(0, 100));
            return "redirect:/";
        }


        return "redirect:/";
    }

    private UUID parseChatroomUuid(String chatroomUuid) {
        try
        {
            return UUID.fromString(chatroomUuid);
        }
        catch(IllegalArgumentException ex){
            return null;
        }
    }

}
