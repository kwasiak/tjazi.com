/**
 * Created by kwasiak on 15/07/15.
 */
/*jshint unused:false*/
var WebSocketClient = function (topic) {

    "use strict";

    var _connectViaWebSocket = null;
    var _sendMessageOverWebSocket = null;
    var _disconnectWebSocket = null;

    if (!topic) {
        console.error("Topic is null or empty.");
    }

    else {
        var endpointName = "/messages";
        var targetTopic = topic;
        var stompClient = null;

        _connectViaWebSocket = function (connectCallback, messageReceiveCallback) {
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

        _sendMessageOverWebSocket = function (messageText) {

            if (!messageText) {
                console.error("Message is null or empty.");

                // do nothing if message is empty, null, etc.
                return;
            }

            if (stompClient === null) {
                console.error("stompClient is not initialized");
            } else {
                stompClient.send("/app" + endpointName, {},
                    JSON.stringify({
                        "messageText": messageText,
                        "receiver": targetTopic,
                        "receiverType": "CHATROOM"
                    }));
            }
        };

        _disconnectWebSocket = function () {
            if (stompClient === null) {
                console.error("Tries to disconnect connection, which is already closed.");
            } else {
                stompClient.disconnect();
                stompClient = null;

                console.log("Disconnected...");
            }
        };
    }


    return {
        connectViaWebSocket: _connectViaWebSocket,

        sendMessageOverWebSocket: _sendMessageOverWebSocket,

        disconnectWebSocket: _disconnectWebSocket
    };
};
