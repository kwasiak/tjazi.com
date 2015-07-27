package com.tjazi.security.service;

/**
 * Created by kwasiak on 27/07/15.
 */
public interface SecurityService {

    /**
     * Get name of the user for the current authentication session
     * This is useful for Spring.MVC projects
     * @return Current user name
     */
    String getCurrentUserName();
}
