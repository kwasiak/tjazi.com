package com.tjazi.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwasiak on 18/07/15.
 * Based on: https://github.com/yacekmm/looksok/blob/MyAuthenticationProvider/Spring/SpringSecurity/src/main/java/pl/looksok/spring/security/config/MyAuthenticationProvider.java
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // we don't can about password at this stage - we just need to create user
        // and return his / her Authentication

        String userName = authentication.getName();

        log.debug("Attempting to authenticate user: " + userName);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        // grant "USER" authority by default
        grantedAuths.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(
                userName,
                authentication.getCredentials(),
                grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        // return 'true' for now; /*TODO: check what else can be returned here and when / why */
        return true;
    }
}
