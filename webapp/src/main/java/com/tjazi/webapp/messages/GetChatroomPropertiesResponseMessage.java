package com.tjazi.webapp.messages;

import java.util.List;

/**
 * Created by kwasiak on 25/07/15.
 */
public class GetChatroomPropertiesResponseMessage {

    private GetChatroomPropertiesResult result;
    private String chatroomName;
    private String currentUserName;
    private List<String> chatroomUsers;

    public GetChatroomPropertiesResponseMessage() {
    }

    public GetChatroomPropertiesResponseMessage(GetChatroomPropertiesResult result) {
        this(result, null, null, null);
    }

    public GetChatroomPropertiesResponseMessage(
            GetChatroomPropertiesResult result,
            String chatroomName,
            String currentUserName,
            List<String> chatroomUsers) {
        this.result = result;
        this.chatroomName = chatroomName;
        this.currentUserName = currentUserName;
        this.chatroomUsers = chatroomUsers;
    }

    public GetChatroomPropertiesResult getResult() {
        return result;
    }

    public void setResult(GetChatroomPropertiesResult result) {
        this.result = result;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public List<String> getChatroomUsers() {
        return chatroomUsers;
    }

    public void setChatroomUsers(List<String> chatroomUsers) {
        this.chatroomUsers = chatroomUsers;
    }
}
