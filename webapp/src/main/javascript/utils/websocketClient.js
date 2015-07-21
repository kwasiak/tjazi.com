/**
 * Created by kwasiak on 15/07/15.
 */
/*jshint unused:false*/
var WebSocketClient = function () {

    "use strict";

    var endpointName = "/messages";
    var targetTopic = "chatroom1";
    var stompClient = null;

    var _connectViaWebSocket = function (connectCallback, messageReceiveCallback) {
            var socket = new SockJS(endpointName);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function connectionAck(frame) {
                console.log("websocketClient: " + frame);

                if (connectCallback !== null) {
                    connectCallback();
                }

                stompClient.subscribe('/topic/' + targetTopic, function (message) {

                    console.log("Got new message from the server on topic: " + targetTopic);
                    console.log(message);

                    if (messageReceiveCallback !== null) {

                        var receivedMessageObject = JSON.parse(message.body);
                        // send received message to callback
                        messageReceiveCallback(receivedMessageObject);
                    }
                });
            }, function connectionError(errorFrame) {
                console.error(errorFrame);
            });
        };

    var _sendMessageOverWebSocket = function (messageText) {

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

    var _disconnectWebSocket = function () {
        if (stompClient === null) {
            console.error("Tries to disconnect connection, which is already closed.");
        } else {
            stompClient.disconnect();
            stompClient = null;

            console.log("Disconnected...");
        }
    };

    return {
        connectViaWebSocket: _connectViaWebSocket,

        sendMessageOverWebSocket: _sendMessageOverWebSocket,

        disconnectWebSocket: _disconnectWebSocket
    };
};
