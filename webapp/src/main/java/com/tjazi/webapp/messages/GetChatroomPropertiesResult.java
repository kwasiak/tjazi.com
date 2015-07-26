package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 25/07/15.
 */
public enum  GetChatroomPropertiesResult {

    OK,

    /**
     * Current user doesn't have permission to see the chatroom
     */
    PERMISSION_DENIED,

    /**
     * Chatroom doesn't exist
     */
    UKNOWN_CHATROOM,

    /**
     * Wrong parameters passed to the service
     */
    WRONG_PARAMETERS
}
