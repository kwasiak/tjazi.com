/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("HomeScreenController",
                    ["$state", "$securityService",
                     homeScreenController]);

    function homeScreenController($state, $securityService) {

        console.log($state);

        /* jshint validthis: true */
        var vm = this;

        vm.onStartNewChatroomClick = startNewChatroomClick;

        function startNewChatroomClick() {

            $securityService.isUserAuthenticatedLocalCheck(function(result) {
                if (result === true) {
                    // move directly to the chat creation screen
                    $state.go(StateNames.newChat);
                } else {
                    // move to log-in screen if user is not logged-in
                    $state.go(StateNames.login, {
                        "callbackStateName": StateNames.newChat
                    });
                }
            });
        }
    }
}());