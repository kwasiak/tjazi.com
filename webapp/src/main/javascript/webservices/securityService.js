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
    var AUTHENTICATE_USER_URL = "/security/authenticateuser";

    function SecurityService($http, $rootScope) {

        var _authenticateUser = function (userName, chatroomName, resultCallback) {

            var headers = {
                authorization: "Basic " + btoa(userName + ":" + FAKE_PASSWORD),
                "Chatroom": chatroomName
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

        return {
            authenticateUser : _authenticateUser
        };
    }
}());