/**
 * Created by kwasiak on 27/07/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("LoginScreenController",
                    ["$state", "$stateParams", "$securityService", "$rootScope",
                        loginController]);

    function loginController($state, $stateParams, $securityService, $rootScope) {

        var DEFAULT_CALLBACK_STATE = StateNames.home;

        /* jshint validthis: true */
        var vm = this;

        vm.loginForm = {
            "userName": "",
            "password": ""
        };

        vm.authenticationError = false;

        vm.onLoginClick = onLoginClick;
        vm.onCancelClick = onCancelClick;

        // check if current user is logged-in (as per session data)
        // if he / she is, then move back to the default state
        isCurrentUserLoggedIn(function(result) {
           if (result) {
               $state.go(StateNames.home);
           }
        });


        function onLoginClick() {
            isCurrentUserLoggedIn(function(result) {

                if (result) {
                    redirectToCallbackState();
                } else {

                    var userName = vm.loginForm.userName;
                    var password = vm.loginForm.password;

                    if (userName && password) {
                        $securityService.authenticateUser(
                            userName, password,
                            onAuthenticationComplete);
                    }
                }
            });
        }

        function onAuthenticationComplete(token, isAuthenticated) {
            if (!isAuthenticated) {

                console.error("Authentication has failed");

                // trigger alert showing log-in error
                vm.authenticationError = true;
            } else {
                console.log("Authentication succeed. Token: " + token);

                $rootScope.token = token;
                $rootScope.userName = vm.loginForm.userName;
                $rootScope.authenticated = true;

                redirectToCallbackState();
            }
        }

        function onCancelClick() {

            $state.go(StateNames.home);
        }

        function isCurrentUserLoggedIn(isUserLoggedInCallback) {
            $securityService.isUserAuthenticated(function(result) {
                isUserLoggedInCallback(result && result.isAuthenticated);
            });
        }

        function redirectToCallbackState() {

            if ($stateParams && $stateParams.callbackStateName) {

                var callbackState = $stateParams.callbackStateName;

                console.info("Moving from Login controller to " + callbackState);

                $state.go(callbackState, $stateParams.callbackStateParams);
            } else {
                console.warn("No callback set for Login controller. Moving to " + DEFAULT_CALLBACK_STATE);

                $state.go(StateNames.home);
            }
        }
    }
}());
