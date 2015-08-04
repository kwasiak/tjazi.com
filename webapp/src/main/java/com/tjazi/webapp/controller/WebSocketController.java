package com.tjazi.webapp.controller;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.webapp.messages.ChatMessage;
import com.tjazi.webapp.messages.ChatMessageReceiverType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.UUID;

/**
 * Created by kwasiak on 16/07/15.
 */

@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChatroomService chatroomService;


    @MessageMapping("/messages")
    public void handleChatMessage(Message<Object> message, @Payload ChatMessage chatMessage) throws Exception {

        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);

        if (principal == null) {
            log.error("Got a message, but can't identify the sender. Rejecting message.");
            return;
        }

        String receiver = chatMessage.getReceiver();
        if (receiver == null || receiver.isEmpty()) {
            log.error("Can't figure-out who's the receiver of the message. Rejecting message.");
            return;
        }

        ChatMessageReceiverType receiverType = chatMessage.getReceiverType();
        if (receiverType == null || receiverType == ChatMessageReceiverType.USER) {
            log.error("Got null or unsupported receiver type ('{}'). Rejecting message.", receiverType);
            return;
        }

        String messageSenderName = principal.getName();

        /**********************************
        // tjazi doesn't support user to user yet, so from this point we can assume we have chatroom name
        // as a receiver
         /********************************/

        if (!isUserValidForChatroom(messageSenderName, receiver)) {
            log.error("Got message from user: '{}', but (s)he doesn't have permission to chatroom: '{}' " +
                            "or that chatroom doesn't exists. Dropping message.",
                    messageSenderName, receiver);
            return;
        }

        log.debug("Message accepted. sender: {}, payload: {}, sending to receiver: {} (type: {})",
                messageSenderName, chatMessage.getMessageText(), receiver, receiverType);

        // fill the sender field and re-send to the chat
        chatMessage.setSenderUserName(messageSenderName);

        template.convertAndSend("/topic/" + receiver, chatMessage);
    }

    private boolean isUserValidForChatroom(String userName, String chatroomUuid) {

        // check if that chatroom really exists
        SingleChatroomDriver chatroomDriver = chatroomService.findChatroomByUuid(UUID.fromString(chatroomUuid));

        if (chatroomDriver == null) {
            log.error("Can't find chatroom with given UUID ('{}'). Dropping message.", chatroomUuid);
            return false;
        }

        return chatroomDriver.isUserInChatroom(userName);
    }
}
