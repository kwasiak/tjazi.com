(function () {
    "use strict";

    var app = angular.module("tjaziWebApp", ["ui.router"]);

    var templatesRoot = "templates";
    var templatesSecureRoot = "templatessecure";

    app.config(["$stateProvider", "$urlRouterProvider", "$httpProvider",

                function($stateProvider, $urlRouterProvider, $httpProvider) {
                    $urlRouterProvider.otherwise("/");

                    $stateProvider
                        .state("home", {
                            url: "/",
                            templateUrl: templatesRoot + "/home.html",
                            controller: "HomeScreenController as vm"
                        })
                        .state("chatScreen", {
                            url: "/chatscreen",
                            templateUrl: templatesSecureRoot + "/chatScreen.html",
                            controller: "ChatScreenController as vm"
                        });

                    // make sure all requests will be decorated with the proper header
                    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                }
    ]);
}());