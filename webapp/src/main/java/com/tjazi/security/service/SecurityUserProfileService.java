package com.tjazi.security.service;

import java.util.UUID;

/**
 * Created by kwasiak on 01/08/15.
 */
public interface SecurityUserProfileService {

    boolean areCredentialsValid(UUID profileUuid, String password);
    void registerNewSecurityUserProfile(UUID profileUuid, String password);
}
