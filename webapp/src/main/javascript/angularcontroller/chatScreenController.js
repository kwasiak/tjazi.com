/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("ChatScreenController",
        ["$scope", "$stateParams", "$state", "$chatroomService", "$location",
            chatScreenController]);

    function chatScreenController($scope, $stateParams, $state, $chatroomService, $location) {

        /* jshint validthis: true */
        var vm = this;

        if (!assertInputParametersAreOk()) {
            console.error("There's no chatroomUuid passed to ChatScreenController. Moving back to home");

            navigateToHomeController();
        }

        vm.textBoxMessageText = "";
        vm.allReceivedMessages = [];
        vm.chatroomName = "";
        vm.currentUserName = "";
        vm.webSocketClient = null;
        vm.getJoinChatLink = getJoinChatLink;

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

        // proceed only of entry parameters are ok
        if (assertInputParametersAreOk()) {

            $chatroomService.getChatroomProperties(
                $stateParams.chatroomUuid,
                getChatroomPropertiesResult);
        }

        function getChatroomPropertiesResult(chatroomProperties) {

            if (chatroomProperties && chatroomProperties.result === "OK") {
                vm.chatroomName = chatroomProperties.chatroomName;
                vm.currentUserName = chatroomProperties.currentUserName;

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
            vm.webSocketClient = new WebSocketClient($stateParams.chatroomUuid);

            vm.webSocketClient.connectViaWebSocket(
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
            var topBannerHeight = $('#topNavBar').outerHeight(true);
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
                vm.webSocketClient.sendMessageOverWebSocket(messageText);

                console.log("Sending message: " + messageText);

                vm.textBoxMessageText = "";
            }
        }

        function getJoinChatLink() {
            return $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/chatroom/join/" + $stateParams.chatroomUuid;
        }
    }

}());