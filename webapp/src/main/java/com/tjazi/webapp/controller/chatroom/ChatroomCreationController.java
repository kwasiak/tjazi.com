package com.tjazi.webapp.controller.chatroom;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.security.service.SecurityService;
import com.tjazi.webapp.messages.chatroomcreation.CreateChatroomMessage;
import com.tjazi.webapp.messages.chatroomcreation.CreateChatroomResponseMessage;
import com.tjazi.webapp.messages.chatroomcreation.CreateChatroomResult;
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
public class ChatroomCreationController {

    @Autowired
    private ChatroomService chatroomService;

    @Autowired
    private SecurityService securityService;

    private static final Logger log = LoggerFactory.getLogger(ChatroomCreationController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateChatroomResponseMessage createChatroom(
            @RequestBody CreateChatroomMessage createChatroomMessage) {

        try {
            String chatroomName = createChatroomMessage.getChatroomName();

            log.debug("Got request to create chatroom. Name: '{}'", chatroomName);

            if (chatroomService.isChatroomExist(chatroomName)) {
                return new CreateChatroomResponseMessage(CreateChatroomResult.CHATROOM_EXISTS, null);
            }

            String currentUserName = this.getCurrentLoggedUser();

            if (currentUserName == null || currentUserName.isEmpty()) {
                log.error("Chatroot creation request from user, who is not logged-in.");
                return new CreateChatroomResponseMessage(CreateChatroomResult.PERMISSION_DENIED, null);
            }

            log.debug("Adding administrator to the chatroom. Administrator: '{}'", currentUserName);

            SingleChatroomDriver chatroomDriver = chatroomService.createNewChatroom(chatroomName);

            // because this is new chatroom, there's no need to check if user already exists
            chatroomDriver.addUserToChatroom(currentUserName);

            return new CreateChatroomResponseMessage(CreateChatroomResult.OK,
                    chatroomDriver.getChatroomUuid());
        }
        catch (Exception ex) {

            log.error("Exception when processing CreateChatroomMessage message. Details:\n" + ex);
            return new CreateChatroomResponseMessage(CreateChatroomResult.GENERAL_ERROR, null);
        }
    }

    private String getCurrentLoggedUser() {
        return securityService.getCurrentUserName();
    }
}
