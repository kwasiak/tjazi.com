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

        /*
         /* Mouse and keyboard handlers
         */
        vm.onSubmitMessageClick = sendMessage;
        vm.onKeyUp = handleKeyboardOnNewMessage;

        /* [ENDOF] Mouse and keyboard handlers */


        vm.allReceivedMessages.push(
            {
                "sender": "Krzysztof Wasiak",
                "messageText": "The karma studies1Chicken breasts combines greatly with bloody marshmellow."
            },
            {
                "sender": "Daniel Ogorzalek",
                "messageText": "Freedom views when you hurt with bliss.Ales grow on malaria at prison!Anomaly at the alpha quadrant was the mystery of mineral, fighted to a ship-wide green people."
            }
        );

        var webSocketClient = new WebSocketClient();

        webSocketClient.connectViaWebSocket(
            function connectCallback() {
               console.log("Connected to web socket!");
            },
            function newMessageCallback(message){
                renderNewMessage(message);
            });

        function handleKeyboardOnNewMessage(eventData) {
                if (eventData.keyCode === 13 && !eventData.shiftKey) {
                    sendMessage();
                }
        }

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
                webSocketClient.sendMessageOverWebSocket(messageText);

                console.log("Sending message: " + messageText);

                vm.textBoxMessageText = "";
            }
        }
    }

}());