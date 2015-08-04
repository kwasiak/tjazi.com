package com.tjazi.webapp.messages.chatroomcreation;

import java.util.UUID;

/**
 * Created by kwasiak on 21/07/15.
 */
public class CreateChatroomResponseMessage {

    private CreateChatroomResult createChatroomResult;
    private UUID chatroomUuid;

    public CreateChatroomResponseMessage() {}

    public CreateChatroomResponseMessage(CreateChatroomResult createChatroomResult, UUID chatroomUuid) {
        this.createChatroomResult = createChatroomResult;
        this.chatroomUuid = chatroomUuid;
    }

    public CreateChatroomResult getCreateChatroomResult() {
        return createChatroomResult;
    }

    public void setCreateChatroomResult(CreateChatroomResult createChatroomResult) {
        this.createChatroomResult = createChatroomResult;
    }

    public UUID getChatroomUuid() {
        return chatroomUuid;
    }

    public void setChatroomUuid(UUID chatroomUuid) {
        this.chatroomUuid = chatroomUuid;
    }
}
