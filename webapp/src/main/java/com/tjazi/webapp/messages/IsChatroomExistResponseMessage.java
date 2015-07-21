package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 21/07/15.
 */
public class IsChatroomExistResponseMessage {

    private boolean chatroomExists;

    public IsChatroomExistResponseMessage() { }
    public IsChatroomExistResponseMessage(boolean chatroomExists) { this.chatroomExists = chatroomExists; }

    public boolean isChatroomExists() {
        return chatroomExists;
    }

    public void setChatroomExists(boolean chatroomExists) {
        this.chatroomExists = chatroomExists;
    }
}
