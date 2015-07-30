package com.tjazi.userprofile.service;

import com.tjazi.userprofile.model.SingleUserProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 30/07/15.
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private List<SingleUserProfileData> userProfiles;

    @Autowired
    private SingleUserProfileDataFactory dataFactory;

    public UserProfileServiceImpl() {
        userProfiles = new ArrayList<>();
    }

    @Override
    public SingleUserProfileData getProfileDataByUserName(String userName) {

        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("userName is null or empty");
        }

        return userProfiles.stream()
                .filter(element -> element.getUserName().equalsIgnoreCase(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public SingleUserProfileData getProfileDataByUuid(UUID profileUuid) {

        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null");
        }

        return userProfiles.stream()
                .filter(element -> element.getProfileUuid() == profileUuid)
                .findFirst()
                .orElse(null);
    }

    @Override
    public SingleUserProfileData registerNewProfile(String userName) {

        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("userName is null or empty");
        }

        // check if user is already registered
        SingleUserProfileData profileByName = this.getProfileDataByUserName(userName);

        if (profileByName != null) {
            throw new IllegalArgumentException("userName already present. Please user different user name.");
        }

        SingleUserProfileData profileData = dataFactory.createSingleUserProfileData(userName);

        userProfiles.add(profileData);

        return profileData;
    }
}
