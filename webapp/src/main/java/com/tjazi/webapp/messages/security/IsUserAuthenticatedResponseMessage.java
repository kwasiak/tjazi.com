package com.tjazi.webapp.messages.security;

import java.util.UUID;

/**
 * Created by kwasiak on 27/07/15.
 */
public class IsUserAuthenticatedResponseMessage {

    private boolean isAuthenticated;
    private String authenticationToken;

    public IsUserAuthenticatedResponseMessage() {
    }

    public IsUserAuthenticatedResponseMessage(boolean isAuthenticated, String authenticationToken) {
        this.isAuthenticated = isAuthenticated;
        this.authenticationToken = authenticationToken;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
