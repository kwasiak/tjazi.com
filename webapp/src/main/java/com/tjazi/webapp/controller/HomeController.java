package com.tjazi.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kwasiak on 11/07/15.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", "index", "index.html"}, method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
}
