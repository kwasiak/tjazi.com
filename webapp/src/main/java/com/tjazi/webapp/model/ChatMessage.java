package com.tjazi.webapp.model;

/**
 * Created by kwasiak on 16/07/15.
 */
public class ChatMessage {

    private String messageText;

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
}
