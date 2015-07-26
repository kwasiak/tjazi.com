package com.tjazi.chatroom.service;

import com.tjazi.chatroom.model.SingleChatroomData;

import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 20/07/15.
 */
public class SingleChatroomDriverImpl implements SingleChatroomDriver {

    private SingleChatroomData chatroomData;

    public SingleChatroomDriverImpl(SingleChatroomData chatroomData) {
        if (chatroomData == null) {
            throw new IllegalArgumentException("chatroomData is null");
        }

        this.chatroomData = chatroomData;
    }

    @Override
    public boolean isUserInChatroom(String userName) {

        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("userName is null or empty.");
        }

        return chatroomData.getChatroomUsers()
                .stream()
                .anyMatch(user -> user.equalsIgnoreCase(userName));
    }

    @Override
    public void addUserToChatroom(String userName) {

        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("userName is null or empty.");
        }

        if (this.isUserInChatroom(userName)) {
            throw new IllegalArgumentException("userName is already registered in the chatroom.");
        }

        this.chatroomData.getChatroomUsers().add(userName);
    }

    @Override
    public UUID getChatroomUuid() {
        return chatroomData.getChatroomUuid();
    }

    @Override
    public String getChatroomName() {
        return chatroomData.getChatroomName();
    }

    @Override
    public List<String> getChatroomUsers() {
        return chatroomData.getChatroomUsers();
    }
}
