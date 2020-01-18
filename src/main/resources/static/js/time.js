
var app = angular.module('time', []);

app.controller('myCtrl', function($scope, $interval) {
    $scope.theTime = new Date();
    $interval(function () {
        $scope.theTime = new Date();
    }, 1000);
});
