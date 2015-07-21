package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 21/07/15.
 */
public class CreateChatroomResponseMessage {

    private CreateChatroomResult createChatroomResult;

    public CreateChatroomResponseMessage() {}

    public CreateChatroomResponseMessage(CreateChatroomResult createChatroomResult) {
        this.createChatroomResult = createChatroomResult;
    }

    public CreateChatroomResult getCreateChatroomResult() {
        return createChatroomResult;
    }

    public void setCreateChatroomResult(CreateChatroomResult createChatroomResult) {
        this.createChatroomResult = createChatroomResult;
    }
}
