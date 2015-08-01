package com.tjazi.webapp.messages.userprofile;

/**
 * Created by kwasiak on 30/07/15.
 */
public class CreateUserProfileResponseMessage {

    private CreateUserProfileResult result;

    public CreateUserProfileResult getResult() {
        return result;
    }

    public CreateUserProfileResponseMessage(CreateUserProfileResult result) {
        this.result = result;
    }

    public void setResult(CreateUserProfileResult result) {
        this.result = result;
    }
}
