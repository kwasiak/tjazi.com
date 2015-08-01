package com.tjazi.webapp.config;

import com.tjazi.security.service.SecurityUserProfileService;
import com.tjazi.userprofile.model.SingleUserProfileData;
import com.tjazi.userprofile.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 18/07/15.
 * Based on: https://github.com/yacekmm/looksok/blob/MyAuthenticationProvider/Spring/SpringSecurity/src/main/java/pl/looksok/spring/security/config/MyAuthenticationProvider.java
 */

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecurityUserProfileService securityUserProfileService;

    @Autowired
    private UserProfileService userProfileService;

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // we don't can about password at this stage - we just need to create user
        // and return his / her Authentication

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        String userName = String.valueOf(authToken.getPrincipal());
        String password = String.valueOf(authToken.getCredentials());

        log.info("Attempting to authenticate user: " + userName);

        // first find profile of that user
        SingleUserProfileData profileDataByUserName = userProfileService.getProfileDataByUserName(userName);

        if (profileDataByUserName == null) {
            log.error("Can't find registered profile for user: " + userName);
            throw new BadCredentialsException("Bad Credentials");
        }

        UUID profileUuid = profileDataByUserName.getProfileUuid();

        log.debug("Attempting to validate credentials for user: {}, profile UUID: {}",
                userName, profileUuid);

        // now: validate credentials
        if (!securityUserProfileService.areCredentialsValid(profileUuid, password)) {
            log.error("Invalid credentials given for user: " + userName);
            throw new BadCredentialsException("Bad Credentials");
        }

        log.info("Authentication succeed for user {}, profile UUID: {}", userName, profileUuid);

        List<GrantedAuthority> grantedAuths = this.getAuthorities();

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

    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        // grant "USER" authority by default
        grantedAuths.add(new SimpleGrantedAuthority("USER"));
        
        return grantedAuths;
    }
}
