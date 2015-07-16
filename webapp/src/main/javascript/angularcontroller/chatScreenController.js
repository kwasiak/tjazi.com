/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("ChatScreenController", chatScreenController);

    function chatScreenController() {

        /* jshint validthis: true */
        var vm = this;

        vm.messageText = "";

        /*
        /* Mouse and keyboard handlers
         */
        vm.onSubmitMessageClick = function() {
            sendMessage();
        };

        vm.onKeyUp = function(eventData) {
            if (eventData.keyCode === 13 && !eventData.shiftKey) {
                sendMessage();
            }
        };

        /* [ENDOF] Mouse and keyboard handlers */

        connectViaWebSocket(
            function connectCallback() {
               console.log("Connected to web socket!");
            },
            function newMessageCallback(message){
                renderNewMessage(message);
            });

        function renderNewMessage(message) {
            console.log(message);
        }

        function sendMessage() {
            var messageText = vm.messageText;

            if (messageText !== "") {
                sendMessageOverWebSocket(messageText);

                console.log("Sending message: " + messageText);

                vm.messageText = "";
            }
        }
    }

}());