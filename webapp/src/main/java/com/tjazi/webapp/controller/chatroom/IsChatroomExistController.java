package com.tjazi.webapp.controller.chatroom;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.webapp.messages.IsChatroomExistMessage;
import com.tjazi.webapp.messages.IsChatroomExistResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 26/07/15.
 */
@RestController
@RequestMapping(value = "/chatroom")
public class IsChatroomExistController {

    @Autowired
    private ChatroomService chatroomService;

    private static final Logger log = LoggerFactory.getLogger(IsChatroomExistController.class);

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
}
