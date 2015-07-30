package com.tjazi.userprofile.service;

import com.tjazi.userprofile.model.SingleUserProfileData;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by kwasiak on 30/07/15.
 */

@Service
public class SingleUserProfileDataFactoryImpl implements SingleUserProfileDataFactory {

    @Override
    public SingleUserProfileData createSingleUserProfileData(String userName)
    {
        SingleUserProfileData profileData = new SingleUserProfileData();
        profileData.setProfileUuid(UUID.randomUUID());
        profileData.setUserName(userName);

        return profileData;
    }
}
