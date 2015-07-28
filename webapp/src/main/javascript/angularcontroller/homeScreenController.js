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

        /* jshint validthis: true */
        var vm = this;

        vm.onStartNewChatroomClick = function() {
            $state.go("chatScreen");
        };
    }
}());