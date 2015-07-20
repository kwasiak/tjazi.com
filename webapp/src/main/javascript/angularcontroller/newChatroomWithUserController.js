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
            $chatroomService.isChatroomExist($scope.chatroomName);

            $scope.chatroomAlreadyExistsError = true;
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