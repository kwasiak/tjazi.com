/**
 * Created by kwasiak on 28/07/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("IndexController",
                    ["$scope", "$rootScope", "$securityService", "$state",
                    indexController]);

    function indexController($scope, $rootScope, $securityService, $state) {

        // set authentication details into the $rootscope
        $rootScope.token = window.auth.token;
        $rootScope.userName = window.auth.user;
        $rootScope.authenticated = (window.auth.user && window.auth.token);

        $scope.isUserAuthenticated = isUserAuthenticated;
        $scope.logout = logoutUser;
        $scope.currentUserName = $rootScope.userName;

        $rootScope.$watch("userName", onUserNameChange);
        $rootScope.$watch("authenticated", onAuthenticatedStatusChange);

        function onUserNameChange() {
            $scope.currentUserName = $rootScope.userName;
        }

        function onAuthenticatedStatusChange() {
            if ($rootScope.authenticated) {
                alert("Authenticated!!");
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