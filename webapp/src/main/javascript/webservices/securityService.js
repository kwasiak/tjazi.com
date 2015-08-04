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
    var SECURITY_ROOT_URL = "/security";
    var AUTHENTICATE_USER_URL = SECURITY_ROOT_URL + "/authenticateuser";
    var LOGOUT_USER_URL = "/logout";
    var IS_USER_AUTHENTICATED_URL = SECURITY_ROOT_URL + "/isuserauthenticated";


    function SecurityService($http, $rootScope) {

        var _authenticateUser = function (userName, password, resultCallback) {

            var headers = {
                authorization: "Basic " + btoa(userName + ":" + password)
            };

            $rootScope.token = null;
            $rootScope.authenticated = false;
            $rootScope.userName = null;

            $http.get(AUTHENTICATE_USER_URL, {headers: headers})
                .success(function(result) {
                    if (result.authenticationToken) {
                        $rootScope.token = result.authenticationToken;
                        $rootScope.authenticated = true;
                        $rootScope.userName = userName;
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

        var _logoutUser = function(resultCallback) {

            $rootScope.token = null;
            $rootScope.authenticated = false;
            $rootScope.userName = null;

            $http.post(LOGOUT_USER_URL, {})
                .success(function() {

                    console.log("LogOut successful.");

                    if (resultCallback) {
                        resultCallback(true);
                    }
                })
                .error(function(data) {
                    console.log("LogOut failed. Error data:\n" + data);

                    if (resultCallback) {
                        resultCallback(false);
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

                    $rootScope.token = result.isAuthenticated;
                    $rootScope.authenticated = result.authenticationToken;

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

        /**
         * Check of user is authenticated by checking local variables
         * instead of calling server (like: _isUserAuthenticated)
         */
        var _isUserAuthenticatedLocalCheck = function(resultCallback) {
            if (resultCallback) {
                resultCallback(!!($rootScope.token && $rootScope.authenticated && $rootScope.userName));
            }
        };

        return {
            authenticateUser : _authenticateUser,
            logoutUser: _logoutUser,
            isUserAuthenticated : _isUserAuthenticated,
            isUserAuthenticatedLocalCheck: _isUserAuthenticatedLocalCheck
        };
    }
}());