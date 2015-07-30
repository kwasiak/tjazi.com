package com.tjazi.userprofile.model;

import java.util.UUID;

/**
 * Created by kwasiak on 30/07/15.
 */
public class SingleUserProfileData {

    private UUID profileUuid;
    private String userName;

    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
