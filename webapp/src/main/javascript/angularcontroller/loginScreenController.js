/**
 * Created by kwasiak on 27/07/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("LoginScreenController",
                    ["$state", "$stateParams", "$securityService", loginController]);

    function loginController($state, $stateParams, $securityService) {

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
                    $securityService.authenticateUser(
                        vm.loginForm.userName, vm.loginForm.password,
                        onAuthenticationComplete);
                }
            });
        }

        function onAuthenticationComplete(token, isAuthenticated) {
            if (!isAuthenticated) {

                /* TODO: authentication failed error dialog */
                console.error("Authentication has failed");

                vm.authenticationError = true;
            } else {
                console.log("Authentication succeed. Token: " + token);

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
