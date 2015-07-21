package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 21/07/15.
 */
public enum ChatMessageReceiverType {

    /**
     * Receiver field of ChatMessage specifies another user,
     * so this is 1:1 message
     */
    USER,

    /**
     * Receiver field of ChatMessage specifies chatroom name,
     * so this is 1:many message
     */
    CHATROOM
}
