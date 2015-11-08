angular.module('rentFlicks').controller('SignInController', [ '$scope', '$http', '$location', '$cookies', 'logging', '$rootScope', 'Auth', function($scope, $http, $location, $cookies, logging, $rootScope, Auth) {
		$scope.uname= "";
		$scope.pwd ="";
		$cookies.put('loggedIn',false);
		$scope.visitor = true;
		$scope.logging=$rootScope.logging;
		$scope.signin = function(){
			console.log($scope.uname);
			console.log($scope.pwd);
			var req = {
					 method: 'POST',
					 url: 'http://localhost:8080/signIn',
					 headers: {
					   'Content-Type': 'application/json'
					 },
					 data: { email: $scope.uname, password:$scope.pwd }
					}
			$http(req).then(function(response){
				//success callback
				if (response.status == 200) {
					console.log(response.data)
					$cookies.put('loggedIn', true);
					$cookies.put('loggedEmail', $scope.uname);
					$scope.visitor = false;
					logging.value=true;
					logging.email = $scope.uname;
					$rootScope.logging.value=true;
					$rootScope.logging.email = $scope.uname;
					console.log('after signin : '+$rootScope.logging);
					Auth.setUser({id:response.data.userId, email: response.data.email, name: response.data.firstName + " " + response.data.lastName})
				
				} else {
					alert("Error: Server Returned " + response.status);
				}
			}, function(response){
				//error callback
				alert("Error: Server returned " + response.status);
			});
		};
		$scope.signout = function(){
			$cookies.put("loggedIn", false);
			$scope.visitor = true;
			$cookies.put("email", "");
			logging.value=false;
			logging.email = '';
			$rootScope.logging.value=false;
			$rootScope.logging.email = '';
			Auth.setUser({id:-1, email: '', name: ''})
		};
	} ]);