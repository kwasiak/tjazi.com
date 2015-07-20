package com.tjazi.chatroom.service;

import java.util.UUID;

/**
 * Created by kwasiak on 19/07/15.
 */
public interface ChatroomService {

    boolean isChatroomExist(String chatroomName);
    UUID creteNewChatroom(String chatroomName);
    SingleChatroomDriver findChatroomByUuid(UUID chatroomUuid);


}
