package com.tjazi.webapp.controller;

import com.tjazi.security.service.SecurityService;
import com.tjazi.session.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by kwasiak on 11/07/15.
 */
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = {"/", "index", "index.html"}, method = RequestMethod.GET)
    public String index(Model model) {

        // data, which will be passed to the HTML page
        model.addAttribute("user", securityService.getCurrentUserName());
        model.addAttribute("token", sessionService.getAuthenticationToken());

        return "index";
    }
}
