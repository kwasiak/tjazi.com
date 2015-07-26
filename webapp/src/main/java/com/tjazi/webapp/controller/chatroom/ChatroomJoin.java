package com.tjazi.webapp.controller.chatroom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 26/07/15.
 */
@Controller
@RequestMapping(value = "/chatroom")
public class ChatroomJoin {


    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinChatroom() {
        return "redirect:/";
    }

}
