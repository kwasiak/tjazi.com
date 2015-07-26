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


        /*
                    UI EVENTS WIRING
         */

        $scope.onContinueButtonClick = continueButtonClick;

        // make sure we catch the close event
        // this is jQuery call
        $('#' + DIALOG_ID).on('hidden.bs.modal', modalDialogClosed);


        /*
                    END OF UI EVENTS WIRING
         */

        function modalDialogClosed() {
            resetForm();
        }

        function continueButtonClick() {

            var administratorUserName = $scope.administratorUserName;
            var chatroomName = $scope.chatroomName;

            // time to authenticate user
            $securityService.authenticateUser(administratorUserName, chatroomName,
                authenticationResult);
        }

        function authenticationResult(token, isAuthenticated) {
            console.log("Authentication finished. Token: " + token + ", authenticated?: " + isAuthenticated);

            if (token && token !== "" && isAuthenticated) {

                var chatroomName = $scope.chatroomName;
                $chatroomService.isChatroomExist(chatroomName, isChatroomExistResult);
            }
        }

        function isChatroomExistResult(result) {
            if (result.toString() === "false") {

                var administratorUserName = $scope.administratorUserName;
                var chatroomName = $scope.chatroomName;

                // init chatroom creation
                $chatroomService.createChatroom(chatroomName, administratorUserName, createChatroomResult);

            } else {
                $scope.chatroomAlreadyExistsError = true;
            }
        }

        function createChatroomResult(result) {
            console.log("Chatroom creation finished. Result: " + result);
            $scope.chatroomAlreadyExistsError = false;

            if (result && result.createChatroomResult && result.chatroomUuid) {

                // all pieces in place - move to the chatroom
                moveToChatroom(result.chatroomUuid);
            }
        }

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

        function moveToChatroom(chatroomUuid) {

            // close the dialog
            $('#' + DIALOG_ID).modal('hide');

            // move to the chatroom
            $state.go("chatScreen", {'chatroomUuid': chatroomUuid});
        }
    }
}());