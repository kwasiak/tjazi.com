package com.tjazi.webapp.messages;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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

    /**
     * This method will be used to handle serialization from incomming string value to enum
     * This is needed, because of format of the string value doesn't fall into enum values,
     * there will be nasty exception thrown by Spring in the logs, which is not possible to mitigate.
     *
     * @param receiverType Receiver type (value allowed as per ChatMessageReceiverType enum)
     */
    public void setReceiverType(String receiverType)
    {
        try
        {
            this.receiverType = Enum.valueOf(ChatMessageReceiverType.class, receiverType);
        }
        catch (IllegalArgumentException ex)
        {
            this.receiverType = null;
        }
    }
}
