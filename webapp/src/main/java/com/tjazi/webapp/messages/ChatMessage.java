package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 16/07/15.
 * THis is message record, which is sent via WebSocket to / from the browser
 */
public class ChatMessage {

    private String messageText;
    private String senderUserName;
    private String receiver;
    private ChatMessageReceiverType receiverType;

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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public ChatMessageReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ChatMessageReceiverType receiverType) {
        this.receiverType = receiverType;
    }
}
