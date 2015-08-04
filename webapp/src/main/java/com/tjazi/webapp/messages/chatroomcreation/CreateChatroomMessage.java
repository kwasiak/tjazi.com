package com.tjazi.webapp.messages.chatroomcreation;

/**
 * Created by kwasiak on 21/07/15.
 */
public class CreateChatroomMessage {

    private String chatroomName;
    private String chatroomAdministratorUserName;

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getChatroomAdministratorUserName() {
        return chatroomAdministratorUserName;
    }

    public void setChatroomAdministratorUserName(String chatroomAdministratorUserName) {
        this.chatroomAdministratorUserName = chatroomAdministratorUserName;
    }
}
