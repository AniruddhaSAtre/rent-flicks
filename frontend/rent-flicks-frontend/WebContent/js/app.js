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
app.run(['$rootScope', '$location', '$http','Auth', 'Email', function ($rootScope, $location, $http, Auth, Email) {
    $rootScope.logging = { value: false, email:"" }; 
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            //event.preventDefault();
            $location.path('/home');
        }
        else {
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
	});
app.factory('Email', function($http){
	var key = "9ZHs5JgrtM_LHR3x_DeKJw";
	var apiURL = "https://mandrillapp.com/api/1.0/messages/send.json";
	return{
		sendEmail: function(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, requestType){
			var mailJSON ={
			        "key": key,
			        "message": {
			          "html": "",
			          "text": emailText,
			          "subject": emailSubject,
			          "from_email": "noreply@rentflicks.com",
			          "from_name": emailFromName,
			          "to": [
			            {
			              "email": emailReceiver,
			              "name": emailReceiverName,
			              "type": "to"
			            }
			          ],
			          "important": false,
			          "track_opens": null,
			          "track_clicks": null,
			          "auto_text": null,
			          "auto_html": null,
			          "inline_css": null,
			          "url_strip_qs": null,
			          "preserve_recipients": null,
			          "view_content_link": null,
			          "tracking_domain": null,
			          "signing_domain": null,
			          "return_path_domain": null
			        },
			        "async": false,
			        "ip_pool": "Main Pool"
			    };
			    
			    $http.post(apiURL, mailJSON).
			      success(function(data, status, headers, config) {
			        console.log(requestType + ' email status: ' + status);
			        return true;
			      }).error(function(data, status, headers, config) {
			        alert('error sending '+ requestType + ' email, status: ' + status);
			        return false;
			      });
		}
	}
});