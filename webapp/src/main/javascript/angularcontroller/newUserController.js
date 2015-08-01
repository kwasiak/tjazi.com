/**
 * Created by kwasiak on 30/07/15.
 */

(function () {
    "use strict";

    angular.module(TjaziApplicationName)
        .controller("NewUserController",
                    ["$state", "$userProfileService", newUserController]);

    function newUserController($state, $userProfileService) {

        console.log($state);
        console.log($userProfileService);

        /* jshint validthis: true */
        var vm = this;

        vm.newUserForm = {
            'userName': null,
            'password': null
        };

        vm.duplicatedUserName = "";

        vm.onAddClick = onAddButtonClick;
        vm.onCancelClick = onCancelButtonClick;

        function onAddButtonClick() {

            var userName = vm.newUserForm.userName;
            var password = vm.newUserForm.password;

            if (userName && password)
            {
                $userProfileService.createNewUserProfile(
                    userName, password, profileRegistrationResult);
            }
        }

        function profileRegistrationResult(result) {

            switch (result.result) {
                case "OK":
                    $state.go(StateNames.home);
                    break;

                case "DUPLICATED_USER_NAME":
                    // setting vm.duplicatedUserName will show the Duplicated User error
                    vm.duplicatedUserName = vm.newUserForm.userName;
                    break;

                default:

                    /* TODO: handle exception cases */
                    break;
            }
        }

        function onCancelButtonClick() {
            $state.go(StateNames.home);
        }
    }
}());
