package com.tjazi.webapp.controller.chatroom;

import com.tjazi.chatroom.service.ChatroomService;
import com.tjazi.chatroom.service.SingleChatroomDriver;
import com.tjazi.security.service.SecurityService;
import com.tjazi.webapp.messages.chatroominfo.GetChatroomsForCurrentUserResponseMeesage;
import com.tjazi.webapp.messages.chatroominfo.GetChatroomsForCurrentUserResult;
import com.tjazi.webapp.messages.chatroominfo.SingleChatroomDataForCurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kwasiak on 05/08/15.
 */
@RestController
@RequestMapping(value = "/chatroom")
public class ChatroomsForCurrentUserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ChatroomService chatroomService;

    private static final Logger log = LoggerFactory.getLogger(ChatroomsForCurrentUserController.class);

    public GetChatroomsForCurrentUserResponseMeesage getChatroomsForCurrentUser() {

        String currentUserName = securityService.getCurrentUserName();

        if (currentUserName == null || currentUserName.isEmpty()) {
            log.error("There's no active logged-in user, so no chatrooms can be returned.");
            return new GetChatroomsForCurrentUserResponseMeesage(GetChatroomsForCurrentUserResult.PERMISSION_DENIED, null);
        }

        List<SingleChatroomDriver> chatroomsForUser = chatroomService.getChatroomsForUser(currentUserName);

        log.debug("Extracted {} chatrooms for user {}", chatroomsForUser.size(), currentUserName);

        return new GetChatroomsForCurrentUserResponseMeesage(GetChatroomsForCurrentUserResult.OK,
                mapToSingleChatroomDataForCurrentUser(chatroomsForUser));
    }

    private List<SingleChatroomDataForCurrentUser> mapToSingleChatroomDataForCurrentUser(
            List<SingleChatroomDriver> entryList) {

        return entryList.stream()
                .map(x -> new SingleChatroomDataForCurrentUser(x.getChatroomName(), x.getChatroomUuid()))
                .collect(Collectors.toList());
    }
}
