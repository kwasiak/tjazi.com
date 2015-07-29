package com.tjazi.webapp.controller.security;

import com.tjazi.security.service.SecurityService;
import com.tjazi.session.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 29/07/15.
 */

@Controller
@RequestMapping(value = "/")
public class LogInLogOutUser {

    private static final Logger log = LoggerFactory.getLogger(LogInLogOutUser.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionService sessionService;

    // this method is for handling ONLY [root]/login?logout calls
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginLogOutUser() {

        log.info("Got request to log-out current user. User name: '{}', authentication token: {}",
                securityService.getCurrentUserName(), sessionService.getAuthenticationToken());

        return "index";
    }
}
