angular.module('rentFlicks').controller(
		'SignUpController',
		[
				'$scope',
				'$http',
				function($scope, $http) {

					

							
					$scope.firstName;
					$scope.lastName;
					$scope.email;
					$scope.password;
					$scope.isLoggedIn = false;
					$scope.searchFilter = '$';
					$scope.signUp = function(firstName, lastName, email,
							password, reTypedPassword) {
						
						var req = {
								 method: 'POST',
								 url: 'http://localhost:8080/signUp',
								 headers: {
								   'Content-Type': 'application/json',
								   'Access-Control-Request-Headers': '*'
								 },
								 data: { email: email, firstName: firstName, lastName: lastName, password:password }
								}
						$http(req).then(function(response){
							//success callback
							if (response.status == 200) {
								alert("Sign Up Successful!");
							} else {
								alert("Error: Server Returned " + response.status);
							}
						}, function(response){
							//error callback
							alert("Error: Server returned " + response.status);
						});
						

					};
				} ]);