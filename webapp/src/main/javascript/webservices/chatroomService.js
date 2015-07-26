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
    var GET_CHATROOM_PROPERTIES_URL = "/chatroom/properties";

    function ChatroomService($http) {

        function _isChatroomExist(chatroomName, existsResultCallback) {
            var postObject = {"chatroomName": chatroomName};
            $http.post(IS_CHAT_EXIST_URL, postObject)
                .success(function(data) {

                    console.log("_isChatroomExist, got success response from server. Data: " + data);

                    if (existsResultCallback) {
                        existsResultCallback(data.chatroomExists);
                    }
                })
                .error(reportHttpErrorToConsole);
        }

        function _createChatroom(chatroomName, administratorUserName, resultCallback) {

            var postObject =
            {
                "chatroomName" : chatroomName,
                "chatroomAdministratorUserName": administratorUserName
            };

            $http.post(CREATE_CHATROOM_URL, postObject)
                .success(function(creationResult) {
                    console.log("_createChatroom, got success response from server. Data: " + creationResult);

                    if (resultCallback) {
                        resultCallback(creationResult);
                    }
                })
                .error(reportHttpErrorToConsole);
        }

        function _getChatroomProperties(chatroomUuid, resultCallback) {
            if (!chatroomUuid) {
                console.error("Chatroom UUID is null or not set");
            }

            var postObject = {
                "chatroomUuid": chatroomUuid
            };

            $http.post(GET_CHATROOM_PROPERTIES_URL, postObject)
                .success(function(data){
                    console.log("_getChatroomProperties: received data. Chatroom name: " + data.chatroomName);

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
            createChatroom : _createChatroom,
            getChatroomProperties: _getChatroomProperties
        };
    }
}());