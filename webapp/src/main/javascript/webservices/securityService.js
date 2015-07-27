/**
 * Created by kwasiak on 21/07/15.
 */

(function () {
    "use strict";

    angular.module("webServices")
        .factory("$securityService",
        ["$http", "$rootScope", SecurityService]);

    // password won't be validated anyway
    // it's just a matter of adding user to the chatroom
    var FAKE_PASSWORD = "password";
    var SECURITY_ROOT_URL = "/security";
    var AUTHENTICATE_USER_URL = SECURITY_ROOT_URL + "/authenticateuser";
    var IS_USER_AUTHENTICATED_URL = SECURITY_ROOT_URL + "/isuserauthenticated";

    function SecurityService($http, $rootScope) {

        var _authenticateUser = function (userName, resultCallback) {

            var headers = {
                authorization: "Basic " + btoa(userName + ":" + FAKE_PASSWORD)
            };

            $rootScope.token = null;
            $rootScope.authenticated = false;

            $http.get(AUTHENTICATE_USER_URL, {headers: headers})
                .success(function(result) {
                    if (result.authenticationToken) {
                        $rootScope.token = result.authenticationToken;
                        $rootScope.authenticated = true;
                    }

                    if (resultCallback) {
                        resultCallback($rootScope.token, $rootScope.authenticated);
                    }
                })
                .error(function() {
                    console.error("Authentication error");

                    if (resultCallback) {
                        resultCallback($rootScope.token, $rootScope.authenticated);
                    }
                });
        };

        /**
         * Check if current user (as per session) is already authenticated
         * @param resultCallback - Function to be called when result is back
         * @private
         */
        var _isUserAuthenticated = function(resultCallback) {
            $http.get(IS_USER_AUTHENTICATED_URL)
                .success(function(result){
                    if (resultCallback) {
                        resultCallback(result);
                    }
                })
                .error(function() {
                    console.error("Problem with getting authentication status for the user.");

                    if (resultCallback) {
                        resultCallback(null);
                    }
                });

        };

        return {
            authenticateUser : _authenticateUser,
            isUserAuthenticated : _isUserAuthenticated
        };
    }
}());