package com.tjazi.session.service;

/**
 * Created by kwasiak on 27/07/15.
 */
public interface SessionService {

    String getAuthenticationToken();
    void setAuthenticationToken(String authenticationToken);
}
