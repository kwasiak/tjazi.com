package com.tjazi.chatroom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 19/07/15.
 */
public class SingleChatroomData {

    private String chatroomName;
    private UUID chatroomUuid;
    private List<String> chatroomUsers = new ArrayList<>();

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public UUID getChatroomUuid() {
        return chatroomUuid;
    }

    public void setChatroomUuid(UUID chatroomUuid) {
        this.chatroomUuid = chatroomUuid;
    }

    public List<String> getChatroomUsers() {
        return chatroomUsers;
    }
}
