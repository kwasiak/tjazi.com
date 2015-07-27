package com.tjazi.session.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by kwasiak on 27/07/15.
 */

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private HttpSession httpSession;

    private final String AUTHENTICATION_TOKEN_PROPERTY = "AUTHENTICATION_TOKEN";

    @Override
    public String getAuthenticationToken() {
        return Objects.toString(httpSession.getAttribute(AUTHENTICATION_TOKEN_PROPERTY), null);
    }

    @Override
    public void setAuthenticationToken(String authenticationToken) {
        httpSession.setAttribute(AUTHENTICATION_TOKEN_PROPERTY, authenticationToken);
    }
}
