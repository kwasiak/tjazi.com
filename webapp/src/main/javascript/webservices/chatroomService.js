/**
 * Created by kwasiak on 20/07/15.
 */

(function () {
    "use strict";

    angular
        .module("webServices")
        .factory("$chatroomService",
        ["$http", ChatroomService]);

    var IS_CHAT_EXIST_URL = "/chatroom/isexist";
    var CREATE_CHATROOM_URL = "/chatroom/create";

    function ChatroomService($http) {

        function _isChatroomExist(chatroomName, existsResultCallback) {
            var postObject = {"chatroomName": chatroomName};
            $http.post(IS_CHAT_EXIST_URL, postObject)
                .success(function(data) {

                    console.log("_isChatroomExist, got success response from server. Data: " + data);

                    if (existsResultCallback) {
                        existsResultCallback(data);
                    }
                })
                .error(reportHttpErrorToConsole);
        }

        function _createChatroom(chatroomName, administratorUserName, resultCallback) {

            var postObject = {"chatroomName" : chatroomName, "administratorUserName": administratorUserName};

            $http.post(CREATE_CHATROOM_URL, postObject)
                .success(function(data) {
                    console.log("_createChatroom, got success response from server. Data: " + data);

                    if (resultCallback) {
                        resultCallback(data);
                    }
                })
                .error(reportHttpErrorToConsole);
        }

        function reportHttpErrorToConsole(data, status, headers) {
            console.error("_isChatroomExist, got error response.\n" + data + "\n" + status + "\n" + headers);
        }

        return {
            isChatroomExist : _isChatroomExist,
            createChatroom : _createChatroom
        };
    }
}());