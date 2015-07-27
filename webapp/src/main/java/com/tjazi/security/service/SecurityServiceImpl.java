package com.tjazi.security.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    public String getCurrentUserName()
    {
        Authentication authentication = this.getAuthenticationContext();

        return (authentication != null &&
                authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken))

                // get a name of the authentication IF it is not anonymous
                ? getAuthenticationContext().getName() : null;
    }

    private Authentication getAuthenticationContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
