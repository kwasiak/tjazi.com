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
        initControllerModels();

        $scope.onContinueButtonClick = function() {

            var chatroomName = $scope.chatroomName;
            var administratorUserName = $scope.administratorUserName;

            $chatroomService.isChatroomExist(chatroomName, function(result) {

                if (result.toString() === "false") {
                    // init chatroom creation
                    $chatroomService.createChatroom(chatroomName, administratorUserName, function(result) {
                        console.log("Chatroom creation finished. Result: " + result);
                    });

                } else {
                    $scope.chatroomAlreadyExistsError = true;
                }
            });
        };

        // make sure we catch the close event
        // this is jQuery call
        $('#dlgUserChatName').on('hidden.bs.modal', function() {
            resetForm();
        });

        function initControllerModels() {
            $scope.chatroomName = "";
            $scope.administratorUserName = "";
            $scope.chatroomAlreadyExistsError = false;
        }

        function resetForm() {
            initControllerModels();

            // without this line form won't be updated
            $scope.$apply();
        }
    }
}());