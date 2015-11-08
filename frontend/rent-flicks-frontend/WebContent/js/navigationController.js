angular.module('rentFlicks').controller('NavController', 
['$scope', '$location', function ($scope, $location) {
  $scope.navClass = function (page) {
    var currentRoute = $location.path().substring(1) || 'home';
    return page === currentRoute ? 'active' : '';
  };
  
  $scope.loadHome = function () {
	 
        $location.url('/home');
    };
    
      $scope.loadMovies = function () {
        $location.url('/movies');
    };
    
      $scope.loadRequests = function () {
        $location.url('/requests');
    };
    $scope.loadForum = function () {
        $location.url('/forum');
    };
    
    
}]);