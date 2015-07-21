package com.tjazi.webapp.controller;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.webapp.messages.*;
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
    public IsChatroomExistResponseMessage isChatroomExist(
            @RequestBody IsChatroomExistMessage chatroomData) {

        String chatroomName = chatroomData.getChatroomName();

        log.debug("Checking if chatroom with name: '{}' exists.", chatroomName);

        if (chatroomName == null || chatroomName.isEmpty()) {

            // we have error condition, which should have been dealt with by UI
            // return that chatroom exists

            log.error("Passed null or empty chatroomName!!!");

            return new IsChatroomExistResponseMessage(true);
        }

        Boolean chatroomExists = chatroomService.isChatroomExist(chatroomName);

        log.debug("Chatroom '{}' exists? {}", chatroomName, chatroomExists.toString());

        return new IsChatroomExistResponseMessage(chatroomExists);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateChatroomResponseMessage createChatroom(
            @RequestBody CreateChatroomMessage createChatroomMessage) {

        try {
            String chatroomName = createChatroomMessage.getChatroomName();
            String userName = createChatroomMessage.getChatroomAdministratorUserName();

            log.debug("Got request to create chatroom. Name: '{}'", chatroomName);

            if (chatroomService.isChatroomExist(chatroomName)) {
                return new CreateChatroomResponseMessage(CreateChatroomResult.CHATROOM_EXISTS);
            }

            log.debug("Adding administrator to the chatroom. Administrator: '{}'", userName);

            SingleChatroomDriver chatroomDriver = chatroomService.createNewChatroom(chatroomName);

            // because this is new chatroom, there's no need to check if user already exists
            chatroomDriver.addUserToChatroom(userName);
        }
        catch (Exception ex) {

            log.error("Exception when processing CreateChatroomMessage message. Details:\n" + ex);
            return new CreateChatroomResponseMessage(CreateChatroomResult.GENERAL_ERROR);
        }

        return new CreateChatroomResponseMessage(CreateChatroomResult.OK);
    }
}
