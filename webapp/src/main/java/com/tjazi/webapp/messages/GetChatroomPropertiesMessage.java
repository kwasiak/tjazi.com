package com.tjazi.webapp.messages;

import java.util.UUID;

/**
 * Created by kwasiak on 25/07/15.
 */
public class GetChatroomPropertiesMessage {

    private UUID chatroomUuid;

    public GetChatroomPropertiesMessage() {
    }

    public GetChatroomPropertiesMessage(UUID chatroomUuid) {
        this.chatroomUuid = chatroomUuid;
    }

    public UUID getChatroomUuid() {
        return chatroomUuid;
    }

    public void setChatroomUuid(UUID chatroomUuid) {
        this.chatroomUuid = chatroomUuid;
    }
}
