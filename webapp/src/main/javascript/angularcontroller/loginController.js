/**
 * Created by kwasiak on 27/07/15.
 */

(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("LoginController",
                    ["$state", "$stateParams", loginController]);

    function loginController($state, $stateParams) {

        var DEFAULT_CALLBACK_STATE = "home";

        redirectToCallbackState();

        function redirectToCallbackState() {

            if ($stateParams && $stateParams.callbackStateName) {

                var callbackState

                $state.go($stateParams.callbackStateName, $stateParams.callbackStateParams);
            } else {
                console.warn("No callback set for Login controller. Moving to " + DEFAULT_CALLBACK_STATE);

                $state.go("home");
            }
        }
    }
}());
