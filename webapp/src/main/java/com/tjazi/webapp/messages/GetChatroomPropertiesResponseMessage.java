package com.tjazi.webapp.messages;

import java.util.List;

/**
 * Created by kwasiak on 25/07/15.
 */
public class GetChatroomPropertiesResponseMessage {

    private GetChatroomPropertiesResult result;
    private String chatroomName;
    private List<String> chatroomUsers;

    public GetChatroomPropertiesResponseMessage() {
    }

    public GetChatroomPropertiesResponseMessage(
            GetChatroomPropertiesResult result, String chatroomName, List<String> chatroomUsers) {
        this.result = result;
        this.chatroomName = chatroomName;
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

    public List<String> getChatroomUsers() {
        return chatroomUsers;
    }

    public void setChatroomUsers(List<String> chatroomUsers) {
        this.chatroomUsers = chatroomUsers;
    }
}
