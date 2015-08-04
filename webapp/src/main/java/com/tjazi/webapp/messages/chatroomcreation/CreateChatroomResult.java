package com.tjazi.webapp.messages.chatroomcreation;

/**
 * Created by kwasiak on 21/07/15.
 */

/**
 * Status
 */
public enum CreateChatroomResult {

    /**
     * All went fine, chatroom is created
     */
    OK,

    /**
     * Chatroom with this name already exists
     */
    CHATROOM_EXISTS,

    /**
     * Current user not logged-in or can't create a new chat
     */
    PERMISSION_DENIED,

    /**
     * General error, try again later
     */
    GENERAL_ERROR
}
