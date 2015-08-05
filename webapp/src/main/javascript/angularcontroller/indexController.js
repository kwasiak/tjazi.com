/**
 * Created by kwasiak on 28/07/15.
 *
 * This is controller, which is responsible for handling content for main page template (index.html)
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("IndexController",
                    ["$scope", "$rootScope", "$securityService", "$state", "$chatroomService",
                    indexController]);

    function indexController($scope, $rootScope, $securityService, $state, $chatroomService) {

        // set authentication details into the $rootscope
        $rootScope.token = window.auth.token;
        $rootScope.userName = window.auth.user;
        $rootScope.authenticated = (window.auth.user && window.auth.token);

        $scope.isUserAuthenticated = isUserAuthenticated;
        $scope.logout = logoutUser;
        $scope.currentUserName = $rootScope.userName;
        $scope.userChatRooms = [];

        $rootScope.$watch("userName", onUserNameChange);
        $rootScope.$watch("authenticated", onAuthenticatedStatusChange);

        // pub/sub listeners
        $rootScope.$on(PubSubEventNames.chatroomsUpdate, onChatroomsUpdate);

        function onUserNameChange() {
            $scope.currentUserName = $rootScope.userName;
        }

        function onAuthenticatedStatusChange() {
            getChatroomsForCurrentUser();
        }

        function onChatroomsUpdate() {
            getChatroomsForCurrentUser();
        }

        function getChatroomsForCurrentUser() {
            if ($rootScope.authenticated) {
                $chatroomService.getChatroomsForCurrentUser(getChatroomsForCurrentUserResult);
            }
        }

        function getChatroomsForCurrentUserResult(resultData) {

            switch (resultData.result) {
                case "OK":
                    $scope.userChatRooms = resultData.chatrooms;

                    break;

                default:
                    /*TODO: exception handling*/
                    break;
            }
        }

        function isUserAuthenticated() {
            return $rootScope && $rootScope.authenticated;
        }

        function logoutUser() {
            $securityService.logoutUser(function(isLogoutSuccessful) {
                if (isLogoutSuccessful) {
                    $state.go(StateNames.home);
                }
                else {
                    $state.go(StateNames.login);
                }
            });
        }

        console.log($scope);
    }
}());