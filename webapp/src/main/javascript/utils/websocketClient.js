/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    var endpointName = "/messages";
    var targetTopic = "chatroom1";
    var stompClient = null;

    /*jshint unused:false*/
    window.connectViaWebSocket = function (connectCallback, messageReceiveCallback) {
        var socket = new SockJS(endpointName);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function connectionAck(frame) {
            console.log("websocketClient: " + frame);

            if (connectCallback !== null) {
                connectCallback();
            }

            stompClient.subscribe('/topic/' + targetTopic, function(message) {

                console.log("Got new message from the server on topic: " + targetTopic);

                if (messageReceiveCallback !== null) {
                    // send received message to callback
                    messageReceiveCallback(JSON.parse(message.body).content);
                }
            });
        }, function connectionError(errorFrame) {
            console.error(errorFrame);
        });
    };

    window.sendMessageOverWebSocket = function(messageText) {

        if (messageText === null || messageText === "") {
            // do nothing if message is empty
            return;
        }

        if (stompClient === null) {
            console.error("stompClient is not initialized");
        } else {
            stompClient.send("/app" + endpointName, {},
                JSON.stringify({
                    "messageText": messageText
                }));
        }
    };

    window.disconnectWebSocket = function () {
        if (stompClient === null) {
            console.error("Tries to disconnect connection, which is already closed.");
        } else {
            stompClient.disconnect();
            stompClient = null;

            console.log("Disconnected...");
        }
    };
}());
