package com.tjazi.webapp.controller;

import com.tjazi.webapp.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by kwasiak on 16/07/15.
 */

@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/messages")
    @SendTo("/topic/chatroom1")
    public ChatMessage handleChatMessage(ChatMessage message) throws Exception {

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
