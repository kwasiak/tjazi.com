package com.tjazi.webapp.controller.userprofile;

import com.tjazi.userprofile.service.UserProfileService;
import com.tjazi.webapp.messages.userprofile.CreateUserProfileRequestMessage;
import com.tjazi.webapp.messages.userprofile.CreateUserProfileResponseMessage;
import com.tjazi.webapp.messages.userprofile.CreateUserProfileResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(AddUserProfileController.class);

    @RequestMapping(value = "/newprofile", method = RequestMethod.POST)
    public CreateUserProfileResponseMessage createNewUserProfile(
            @RequestBody CreateUserProfileRequestMessage requestMessage) {

        log.info("Profile registration request.");

        if (requestMessage == null) {
            throw new IllegalArgumentException("requestMessage is null");
        }

        String newUserName = requestMessage.getUserName();

        if (newUserName == null || newUserName.isEmpty()) {
            log.error("User name to register is null or empty.");
            return new CreateUserProfileResponseMessage(CreateUserProfileResult.USER_NAME_NULL_OR_EMPTY);
        }

        if (userProfileService.getProfileDataByUserName(newUserName) != null) {
            log.warn("Attempt to register duplicated user name: " + newUserName);
            return new CreateUserProfileResponseMessage(CreateUserProfileResult.DUPLICATED_USER_NAME);
        }

        userProfileService.registerNewProfile(newUserName);

        log.info("Profile registration succeed. User name: " + newUserName);

        return new CreateUserProfileResponseMessage(CreateUserProfileResult.OK);
    }
}
