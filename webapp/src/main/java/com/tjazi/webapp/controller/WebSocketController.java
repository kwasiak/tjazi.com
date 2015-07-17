package com.tjazi.webapp.controller;

import com.tjazi.webapp.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import sun.net.www.MessageHeader;

import java.security.Principal;

/**
 * Created by kwasiak on 16/07/15.
 */

@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/messages")
    @SendTo("/topic/chatroom1")
    public ChatMessage handleChatMessage(
            @Payload ChatMessage message,
           // @DestinationVariable("chatRoomId") String chatRoomId,
            MessageHeaders messageHeaders/*, Principal user*/) throws Exception {

        if (message == null) {
            log.error("handleChatMessage >>> Got null message!!!");
        }

        String messageText = message.getMessageText();

        if (messageText == null || messageText == "") {
            log.error("handleChatMessage >>> Got a message with null or empty content!!!");
        }

        log.info("handleChatMessage >>> Got new message: " + messageText);

        return new ChatMessage(messageText);
    }
}
