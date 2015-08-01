package com.tjazi.webapp.controller.userprofile;

import com.tjazi.userprofile.service.UserProfileService;
import com.tjazi.webapp.messages.userprofile.CreateUserProfileRequestMessage;
import com.tjazi.webapp.messages.userprofile.CreateUserProfileResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kwasiak on 30/07/15.
 */

@RestController
@RequestMapping(value = "/profile")
public class AddUserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/newprofile", method = RequestMethod.POST)
    public CreateUserProfileResponseMessage createNewUserProfile(
            @RequestBody CreateUserProfileRequestMessage requestMessage) {


        return null;
    }
}
