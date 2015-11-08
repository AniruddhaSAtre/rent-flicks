/*angular.module('rentFlicks', ['ngRoute','ui.bootstrap', 'dialogs'])
.controller('dialogServiceTest', function ($scope,$http, $rootScope, $timeout, $dialogs) {
    $scope.clients = []; //Array of client objetcs
    $scope.client = {}; //Single client object

    $scope.launch = function (which,client) {
        var dlg = null;

        alert(client.ClientName);
        dlg = $dialogs.create('/templates/Modal.html', 'whatsYourNameCtrl', {}, { key: false, back: 'static' });
        dlg.result.then(function () {
                $scope.client.ClientName = client.ClientName;
     });
})
.run(['$templateCache', function ($templateCache) {
    $templateCache.put('/templates/Modal.html');
 }]);*/