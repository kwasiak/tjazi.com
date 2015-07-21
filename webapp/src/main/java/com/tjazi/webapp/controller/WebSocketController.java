package com.tjazi.webapp.controller;

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

/**
 * Created by kwasiak on 16/07/15.
 */

@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;


    /*@MessageMapping("/messages")
    @SendTo("/topic/chatroom1")
    public ChatMessage handleChatMessage(
            @Payload ChatMessage message,
           // @DestinationVariable("chatRoomId") String chatRoomId,
            MessageHeaders messageHeaders*//*, Principal user*//*) throws Exception {

        if (message == null) {
            log.error("handleChatMessage >>> Got null message!!!");
        }

        String messageText = message.getMessageText();

        if (messageText == null || messageText == "") {
            log.error("handleChatMessage >>> Got a message with null or empty content!!!");
        }

        log.info("handleChatMessage >>> Got new message: " + messageText);

        return new ChatMessage(messageText);
    }*/

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

        Start coding here!!!

        String messageSenderName = principal.getName();

        log.debug("Message sender: {}, payload: {}", messageSenderName, chatMessage.getMessageText());

        // fill the sender field and re-send to the chat
        chatMessage.setSenderUserName(principal.getName());

        template.convertAndSend("/topic/chatroom1", chatMessage);
    }

    private boolean isUserValidForChatroom(String userName, String chatroomName) {

        /*TODO: add more logic checking if user belongs to the particular group */
        return true;
    }
}
