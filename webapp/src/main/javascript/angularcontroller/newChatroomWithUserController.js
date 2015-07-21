/**
 * Created by kwasiak on 20/07/15.
 */
(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("NewChatroomWithUserController",
                ["$chatroomService", "$securityService", "$scope", "$state", chatroomWithUserController]);

    function chatroomWithUserController($chatroomService, $securityService, $scope, $state) {

        var DIALOG_ID = "dlgUserChatName";

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
                        $scope.chatroomAlreadyExistsError = false;

                        // time to authenticate user
                        $securityService.authenticateUser(administratorUserName, chatroomName,
                            function(token, isAuthenticated) {
                                console.log("Authentication finished. Token: " + token + ", authenticated?: " + isAuthenticated);

                                if (token && token !== "" && isAuthenticated) {

                                    // close the dialog
                                    $('#' + DIALOG_ID).modal('hide');

                                    // move to the chatroom
                                    $state.go("chatScreen");
                                }
                            });
                    });

                } else {
                    $scope.chatroomAlreadyExistsError = true;
                }
            });
        };

        // make sure we catch the close event
        // this is jQuery call
        $('#' + DIALOG_ID).on('hidden.bs.modal', function() {
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