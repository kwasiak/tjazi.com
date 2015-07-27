(function () {
    "use strict";

    var app = angular.module(
            "tjaziWebApp",
            ["webServices", "ui.router"]);

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
                            controller: "ChatScreenController as vm",
                            params: { chatroomUuid: null }
                        })
                        .state("loginScreen", {
                            url: "/login",
                            templateUrl: templatesRoot + "/loginScreen.html",
                            controller: "LoginController as vm",
                            params:
                            {
                                // state, which has to be called after log-in is completed
                                "callbackStateName": null,

                                // parameters, which have to be sent to the callbackStateName
                                "callbackStateParams": null
                            }
                        });

                    // make sure all requests will be decorated with the proper header
                    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                }
    ]);
}());