/**
 * Created by kwasiak on 20/07/15.
 */
(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("NewChatroomWithUserController",
                ["$chatroomService", "$scope", chatroomWithUserController]);

    function chatroomWithUserController($chatroomService, $scope) {

        /* jshint validthis: true */

        $scope.chatroomName = "";
        $scope.administratorUserName = "";

        $scope.onContinueButtonClick = function() {
            $chatroomService.isChatroomExist($scope.chatroomName);
        };
    }
}());