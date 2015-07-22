package com.tjazi.chatroom.service;

import java.util.UUID;

/**
 * Created by kwasiak on 19/07/15.
 */
public interface ChatroomService {

    boolean isChatroomExist(String chatroomName);

    /**
     * Create single chatroom and return its driver
     * @param chatroomName - Name of the chatroom
     * @return Chatroom driver
     */
    SingleChatroomDriver createNewChatroom(String chatroomName);

    SingleChatroomDriver findChatroomByUuid(UUID chatroomUuid);

    SingleChatroomDriver findChatroomByName(String chatroomName);


}
