/**
 * This is User Profile service client
 */

(function () {
    "use strict";

    angular.module("webServices")
        .factory("$userProfileService",
    ["$http", UserProfileService]);

    var USER_PROFILE_ROOT_URL = "/profile";
    var USER_PROFILE_NEW_PROFILE = USER_PROFILE_ROOT_URL + "/newprofile";

    function UserProfileService($http) {

        var _createNewUserProfile = function(userName, password, resultCallback) {

            $http.post(USER_PROFILE_NEW_PROFILE, {
                'userName' : userName,
                'password' : password
            })
                .success(function(result){

                    if (resultCallback) {
                        resultCallback(result);
                    }
                })
                .error(function(data) {
                    console.error("Got error on posting to UserProfileService. Url: " + USER_PROFILE_NEW_PROFILE +
                        ", error data:\n" + data);
                });

        };

        return {
            createNewUserProfile : _createNewUserProfile
        };
    }

}());