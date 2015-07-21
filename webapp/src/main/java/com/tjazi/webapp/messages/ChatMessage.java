package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 16/07/15.
 */
public class ChatMessage {

    private String messageText;
    private String senderUserName;

    public ChatMessage() { }

    public ChatMessage(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }
}
