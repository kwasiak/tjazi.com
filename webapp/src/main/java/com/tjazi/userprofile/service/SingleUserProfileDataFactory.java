package com.tjazi.userprofile.service;

import com.tjazi.userprofile.model.SingleUserProfileData;

/**
 * Created by kwasiak on 30/07/15.
 */
public interface SingleUserProfileDataFactory {

    SingleUserProfileData createSingleUserProfileData(String userName);
}
