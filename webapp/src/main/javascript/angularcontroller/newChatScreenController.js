/**
 * Created by kwasiak on 02/08/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("NewChatScreenController",
            ["$state", "$chatroomService", newChatScreenController]);

    function newChatScreenController($state, $chatroomService) {
        console.log($state);

        /* jshint validthis: true */
        var vm = this;

        vm.newChatForm = {
            "chatName": ""
        };

        vm.duplicatedChatName = "";
        vm.generalError = false;

        vm.onAddClick = onAddButtonClick;
        vm.onCancelClick = onCancelButtonClick;

        function onAddButtonClick() {
            var newChatName = vm.newChatForm.chatName;

            if (newChatName) {
                $chatroomService.createChatroom(newChatName, chatCreationResult);
            }
        }

        function chatCreationResult(result) {

            switch (result.createChatroomResult) {
                case "OK":
                    resetErrors();

                    $state.go(StateNames.chat, {
                        chatroomUuid: result.chatroomUuid
                    });

                    break;

                case "CHATROOM_EXISTS":
                    vm.duplicatedChatName = vm.newChatForm.chatName;
                    break;

                default:
                    // some general error
                    vm.generalError = true;
                    break;
            }
        }

        function resetErrors() {
            vm.duplicatedChatName = "";
            vm.generalError = false;
        }

        function onCancelButtonClick() {
            $state.go(StateNames.home);
        }
    }
}());
