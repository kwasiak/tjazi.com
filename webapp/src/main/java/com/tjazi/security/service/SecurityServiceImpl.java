package com.tjazi.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by kwasiak on 27/07/15.
 *
 * This is wrapper (facade) around SecurityContextHolder
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String getCurrentUserName() {
        return getAuthenticationContext().getName();
    }

    private Authentication getAuthenticationContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
