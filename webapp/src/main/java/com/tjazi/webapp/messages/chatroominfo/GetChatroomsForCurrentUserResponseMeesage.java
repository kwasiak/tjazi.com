package com.tjazi.webapp.messages.chatroominfo;

import com.tjazi.webapp.messages.GetChatroomPropertiesResult;

import java.util.List;

/**
 * Created by kwasiak on 05/08/15.
 */
public class GetChatroomsForCurrentUserResponseMeesage {

    private GetChatroomsForCurrentUserResult result;
    private List<SingleChatroomDataForCurrentUser> chatrooms;

    public GetChatroomsForCurrentUserResponseMeesage() {
    }

    public GetChatroomsForCurrentUserResponseMeesage(GetChatroomsForCurrentUserResult result,
                                                     List<SingleChatroomDataForCurrentUser> chatrooms) {
        this.result = result;
        this.chatrooms = chatrooms;
    }

    public GetChatroomsForCurrentUserResult getResult() {
        return result;
    }

    public void setResult(GetChatroomsForCurrentUserResult result) {
        this.result = result;
    }

    public List<SingleChatroomDataForCurrentUser> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(List<SingleChatroomDataForCurrentUser> chatrooms) {
        this.chatrooms = chatrooms;
    }
}
