// keep name of the states in the global variable
window.StateNames =
    {
        home: "home",
        login: "loginScreen",
        newChat: "newChatScreen",
        chat: "chatScreen",
        newUser: "newUserScreen"
    };

// names of the events, which are exchanged between controllers via $broadcast / $on
window.PubSubEventNames =
{
    chatroomsUpdate: "chatrooms-update"
};

window.TjaziApplicationName = "tjaziWebApp";

(function () {
    "use strict";

    var app = angular.module(
            TjaziApplicationName,
            ["webServices", "ui.router"]);

    var templatesRoot = "templates";
    var templatesSecureRoot = "templatessecure";

    app.config(["$stateProvider", "$urlRouterProvider", "$httpProvider",

                function($stateProvider, $urlRouterProvider, $httpProvider) {
                    // main routing configuration
                    $urlRouterProvider.otherwise("/");

                    $stateProvider
                        .state(StateNames.home, {
                            url: "/",
                            templateUrl: templatesRoot + "/home.html",
                            controller: "HomeScreenController as vm"
                        })
                        .state(StateNames.newChat, {
                            url: "/newchat",
                            templateUrl: templatesSecureRoot + "/newChatScreen.html",
                            controller: "NewChatScreenController as vm"
                        })
                        .state(StateNames.chat, {
                            url: "/chatscreen/:chatroomUuid",
                            templateUrl: templatesSecureRoot + "/chatScreen.html",
                            controller: "ChatScreenController as vm",
                            params: { chatroomUuid: null }
                        })
                        .state(StateNames.login, {
                            url: "/login",
                            templateUrl: templatesRoot + "/loginScreen.html",
                            controller: "LoginScreenController as vm",
                            params:
                            {
                                // state, which has to be called after log-in is completed
                                "callbackStateName": null,

                                // parameters, which have to be sent to the callbackStateName
                                "callbackStateParams": null
                            }
                        })
                        .state(StateNames.newUser, {
                            url: "/newuser",
                            templateUrl: templatesRoot + "/newUserScreen.html",
                            controller: "NewUserController as vm"
                        });

                    // make sure all requests will be decorated with the proper header
                    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                }
    ]);
}());