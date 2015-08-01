package com.tjazi.security.service;

import com.tjazi.security.model.SingleSecurityUserProfileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kwasiak on 01/08/15.
 */
@Service
public class SecurityUserProfileServiceImpl implements SecurityUserProfileService {

    private List<SingleSecurityUserProfileData> securityUserProfileData;

    public SecurityUserProfileServiceImpl() {

        securityUserProfileData = new ArrayList<>();
    }

    @Override
    public boolean areCredentialsValid(UUID profileUuid, String password) {

        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password is null or empty");
        }

        return securityUserProfileData.stream()
                .anyMatch(x -> x.getProfileUuid().equals(profileUuid) && x.getUserPassword()
                        .equals(password));
    }

    @Override
    public void registerNewSecurityUserProfile(UUID profileUuid, String password) {
        if (profileUuid == null) {
            throw new IllegalArgumentException("profileUuid is null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password is null or empty");
        }

        // make sure there's no duplicate record
        if (securityUserProfileData.stream().anyMatch(x -> x.getProfileUuid().equals(profileUuid))) {
            throw new IllegalArgumentException(
                    String.format("Security profile for user profile UUID: %s is already registered",
                            profileUuid.toString()));
        }

        securityUserProfileData.add(new SingleSecurityUserProfileData(profileUuid, password));
    }
}
