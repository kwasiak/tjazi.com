package com.tjazi.chatroom.service;

import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 20/07/15.
 * Interface responsible for managing single Chatroom
 */
public interface SingleChatroomDriver {

    boolean isUserInChatroom(String userName);
    void addUserToChatroom(String userName);

    UUID getChatroomUuid();
    String getChatroomName();
    List<String> getChatroomUsers();
}
