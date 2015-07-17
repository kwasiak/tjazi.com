/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("ChatScreenController",
        ["$scope", chatScreenController]);

    function chatScreenController($scope) {

        /* jshint validthis: true */
        var vm = this;

        vm.textBoxMessageText = "";

        vm.allReceivedMessages = [];

        vm.allReceivedMessages.push({
           "sender": "Krzysztof Wasiak",
            "messageText": "Sample text"
        });

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
            // add new message to the collection of the messages
            console.log("Adding new message to the collection. Message: " + message);
            vm.allReceivedMessages.push(
                {
                    "sender": "[Unknown]",
                    "messageText": message
                }
            );

            // make sure you update the binding
            $scope.$apply();
        }

        function sendMessage() {
            var messageText = vm.textBoxMessageText;

            if (messageText !== "") {
                sendMessageOverWebSocket(messageText);

                console.log("Sending message: " + messageText);

                vm.textBoxMessageText = "";
            }
        }
    }

}());