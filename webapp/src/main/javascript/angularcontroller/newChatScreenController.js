/**
 * Created by kwasiak on 02/08/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("NewChatScreenController",
            ["$state", newChatScreenController]);

    function newChatScreenController($state) {
        console.log($state);


    }
}());
