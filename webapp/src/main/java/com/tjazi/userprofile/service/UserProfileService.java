package com.tjazi.userprofile.service;

import com.tjazi.userprofile.model.SingleUserProfileData;

import java.util.UUID;

/**
 * Created by kwasiak on 30/07/15.
 */
public interface UserProfileService {

    /**
     * Get user profile data by the userName.
     * User name is case-insensitive! (Carlos, CARLOS, CArlos - are considered as the same user names)
     * @param userName
     * @return
     */
    SingleUserProfileData getProfileDataByUserName(String userName);
    SingleUserProfileData getProfileDataByUuid(UUID profileUuid);

    SingleUserProfileData registerNewProfile(String userName);
}
