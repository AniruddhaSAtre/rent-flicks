var app = angular.module('rentFlicks', ['ngRoute', 'ngCookies', 'ui.bootstrap.modal']);
app.config(
		function($routeProvider, $locationProvider, $httpProvider) {

			$routeProvider.when('/home', {
				templateUrl : 'HTMLPartialScreens/catalog.html',
				controller : 'CatalogController'
			});
			$routeProvider.when('/movies', {
				templateUrl : 'HTMLPartialScreens/myMovies.html',
				controller : 'MoviesController'
			});
			$routeProvider.when('/requests', {
				templateUrl : 'HTMLPartialScreens/myRequests.html',
				controller : 'RequestsController'
			});
			$routeProvider.when('/forum', {
				templateUrl : 'HTMLPartialScreens/forum.html',
				controller : 'ForumController'
			});
			$routeProvider.otherwise({
				redirectTo : '/home',
				controller : 'CatalogController',
			});
		});
app.value('logging', {value:false, email:''});
app.run(['$rootScope', '$location','Auth', function ($rootScope, $location, Auth) {
    $rootScope.logging = { value: false, email:"" }; 
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            console.log('DENY');
            console.log($location.url());
            //event.preventDefault();
            $location.path('/home');
        }
        else {
        
            console.log('ALLOW');
            console.log($location.url());
            var url = $location.url();
            $location.path(url);
            
        }
    });
    
}]);
app.factory('Auth', function(){
	var user = { id:-1, email:'', name:''};

	return{
	    setUser : function(User){
	        user.id = User.id;
	        user.email = User.email;
	        user.name = User.name;
	    },
	    isLoggedIn : function(){
	    	if(user.id == -1)
	    		return false;
	    	return true;
	    },
	    getUser: function(){
	    	return user;
	    }
	  }
	})