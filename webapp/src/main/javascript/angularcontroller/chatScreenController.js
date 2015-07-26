/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module("tjaziWebApp")
        .controller("ChatScreenController",
        ["$scope", "$stateParams", "$state", "$chatroomService", chatScreenController]);

    function chatScreenController($scope, $stateParams, $state, $chatroomService) {

        /* jshint validthis: true */
        var vm = this;

        if (!assertInputParametersAreOk()) {
            console.error("There's no chatroomUuid passed to ChatScreenController. Moving back to home");

            navigateToHomeController();
        }

        vm.textBoxMessageText = "";
        vm.allReceivedMessages = [];
        vm.chatroomName = "";

        /*
         /* Mouse and keyboard handlers
         */
        vm.onSubmitMessageClick = sendMessage;
        vm.onKeyUp = handleKeyboardOnNewMessage;

        /* [ENDOF] Mouse and keyboard handlers */

        resizeChatWindow();

        $(window).load(function() {
            resizeChatWindow();
        });

        $(window).resize(function(){ // On resize
            resizeChatWindow();
        });

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

        // proceed only of entry parameters are ok
        if (assertInputParametersAreOk()) {

            $chatroomService.getChatroomProperties(
                $stateParams.chatroomUuid,
                getChatroomPropertiesResult);
        }

        function getChatroomPropertiesResult(chatroomProperties) {

            if (chatroomProperties && chatroomProperties.result === "OK") {
                vm.chatroomName = chatroomProperties.chatroomName;

                connectViaWebSocket();

                // all set to go...

            } else {
                navigateToHomeController();
            }
        }

        function navigateToHomeController() {
            $state.go("home");
        }

        function connectViaWebSocket() {
            var webSocketClient = new WebSocketClient();

            webSocketClient.connectViaWebSocket(
                function connectCallback() {
                    console.log("Connected to web socket!");
                },
                function newMessageCallback(messageObject) {
                    renderNewMessage(messageObject);
                });
        }

        function assertInputParametersAreOk() {
            return $stateParams && $stateParams.chatroomUuid;
        }

        function resizeChatWindow() {
            var topBannerHeight = $('#topBanner').outerHeight(true);
            var charHeaderHeight = $('#chatHeader').outerHeight(true);
            var newMessageHeight = $('#chatNewMessage').outerHeight(true);
            var marginAndAlignment = 20 * 2 + 10;

            var otherElementsSizeY = topBannerHeight + newMessageHeight + charHeaderHeight + marginAndAlignment;
            $('#chatHistory').css({'height':(($(window).height() - otherElementsSizeY))+'px'});
        }

        function handleKeyboardOnNewMessage(eventData) {
                if (eventData.keyCode === 13 && !eventData.shiftKey) {
                    sendMessage();
                }
        }

        function renderNewMessage(messageObject) {
            // add new message to the collection of the messages
            console.log("Adding new message to the collection. Message: " + messageObject);
            vm.allReceivedMessages.push(
                {
                    "sender": messageObject.senderUserName,
                    "messageText": messageObject.messageText
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