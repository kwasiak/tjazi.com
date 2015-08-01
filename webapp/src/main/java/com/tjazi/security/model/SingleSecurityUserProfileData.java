package com.tjazi.security.model;

import java.util.UUID;

/**
 * Created by kwasiak on 01/08/15.
 */
public class SingleSecurityUserProfileData {

    private UUID profileUuid;
    private String userPassword;

    public SingleSecurityUserProfileData() {
    }

    public SingleSecurityUserProfileData(UUID profileUuid, String userPassword) {
        this.profileUuid = profileUuid;
        this.userPassword = userPassword;
    }

    public UUID getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(UUID profileUuid) {
        this.profileUuid = profileUuid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
