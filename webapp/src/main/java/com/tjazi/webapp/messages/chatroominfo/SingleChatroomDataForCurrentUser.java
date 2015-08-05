package com.tjazi.webapp.messages.chatroominfo;

import java.util.UUID;

/**
 * Created by kwasiak on 05/08/15.
 */
public class SingleChatroomDataForCurrentUser {

    private String chatroomName;
    private UUID chatroomUuid;

    public SingleChatroomDataForCurrentUser() {
    }

    public SingleChatroomDataForCurrentUser(String chatroomName, UUID chatroomUuid) {
        this.chatroomName = chatroomName;
        this.chatroomUuid = chatroomUuid;
    }

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
}
