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

        $scope.isUserAuthenticated = isUserAuthenticated;
        $scope.currentUserName = $rootScope.userName;

        function isUserAuthenticated() {
            return $rootScope && $rootScope.authenticated;
        }

        console.log($scope);
    }
}());