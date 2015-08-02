/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("HomeScreenController",
                    ["$state",
                     homeScreenController]);

    function homeScreenController($state) {

        console.log($state);

        /* jshint validthis: true */
        var vm = this;

        vm.onStartNewChatroomClick = startNewChatroomClick;

        function startNewChatroomClick() {

            // move to log-in screen if user is not logged-in

            // otherwise move directly to the chat creation screen

        }
    }
}());