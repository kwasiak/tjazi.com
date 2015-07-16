/**
 * Created by kwasiak on 15/07/15.
 */
(function () {
    "use strict";

    var endpointName = "/messages";
    var targetTopic = "chatroom1";
    var stompClient = null;

    function connect(connectCallback, messageReceiveCallback) {
        var socket = new SockJS(endpointName);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            Console.log("websocketClient: " + frame);

            connectCallback();

            stompClient.subscribe('/topic/' + targetTopic, function(message) {
                // send received message to callback
                messageReceiveCallback(JSON.parse(message.body).content);
            });
        });
    }

    function disconnect() {
        if (stompClient == null) {
            log.error("Tries to disconnect connection, which is already closed.")
        } else {
            stompClient.disconnect();
            stompClient = null;

            console.log("Disconnected...")
        }
    }
}());
