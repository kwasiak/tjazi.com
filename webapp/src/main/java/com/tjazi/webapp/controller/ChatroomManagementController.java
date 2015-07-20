package com.tjazi.webapp.controller;

import com.tjazi.chatroom.service.ChatroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 20/07/15.
 */

@RestController
@RequestMapping(value = "/chatroom")
public class ChatroomManagementController {

    @Autowired
    private ChatroomService chatroomService;

    private static final Logger log = LoggerFactory.getLogger(ChatroomManagementController.class);

    @RequestMapping(value = "/isexist", method = RequestMethod.POST)
    public boolean isChatroomExist(
            @RequestBody String chatroomName) {

        log.debug("Checking if chatroom with name: '{}' exists.", chatroomName);

        if (chatroomName == null || chatroomName.isEmpty()) {

            // we have error condition, which should have been dealt with by UI
            // return that chatroom exists

            log.error("Passed null or empty chatroomName!!!");
            return true;
        }

        Boolean chatroomExists = chatroomService.isChatroomExist(chatroomName);

        log.debug("Chatroom '{}' exists? {}", chatroomName, chatroomExists.toString());

        return chatroomExists;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createChatroom(
            @RequestBody String chatroomName) {

        if (chatroomName == null || chatroomName.isEmpty()) {

            log.error("Passed null or empty chatroomName!!!");
            return "CHATROOM_NAME_EMPTY";
        }

        if (chatroomService.isChatroomExist(chatroomName)) {

            log.error("Chatroom '{}' already exists!!!", chatroomName);
            return "CHATROOM_EXISTS";
        }

        chatroomService.creteNewChatroom(chatroomName);

        return "OK";
    }
}
