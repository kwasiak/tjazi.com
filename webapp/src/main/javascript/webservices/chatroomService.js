/**
 * Created by kwasiak on 20/07/15.
 */

(function () {
    "use strict";

    angular
        .module("webServices")
        .factory("$chatroomService",
        ["$http", ChatroomService]);

    function ChatroomService($http) {

        function _isChatroomExist(chatroomName) {

            console.log("Chatroom name: " + chatroomName);

            console.log($http);

            return false;
        }

        return {
            isChatroomExist : _isChatroomExist
        };
    }
}());