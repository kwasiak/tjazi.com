package com.tjazi.webapp.messages;

/**
 * Created by kwasiak on 21/07/15.
 */
public class AuthenticateUserResponseMessage {

    private String authenticationToken;

    public AuthenticateUserResponseMessage() { }

    public AuthenticateUserResponseMessage(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
