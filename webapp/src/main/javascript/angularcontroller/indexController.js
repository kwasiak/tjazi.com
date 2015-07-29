/**
 * Created by kwasiak on 28/07/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("IndexController",
                    ["$scope", "$rootScope",
                    indexController]);

    function indexController($scope, $rootScope) {

        // set authentication details into the $rootscope
        $rootScope.token = window.auth.token;
        $rootScope.userName = window.auth.user;
        $rootScope.authenticated = (window.auth.user && window.auth.token);

        $scope.isUserAuthenticated = isUserAuthenticated;
        $scope.currentUserName = $rootScope.userName;

        function isUserAuthenticated() {
            return $rootScope && $rootScope.authenticated;
        }

        console.log($scope);
    }
}());