package com.tjazi.webapp.messages.chatroominfo;

/**
 * Created by kwasiak on 05/08/15.
 */
public enum GetChatroomsForCurrentUserResult {

    /**
     * All went well, data contains actual chatrooms
     */
    OK,

    /**
     * Most common case: user not authenticated, blocked, etc.
     */
    PERMISSION_DENIED,

    /**
     * Some general error, most likely: server side exception
     */
    GENERAL_ERROR
}
