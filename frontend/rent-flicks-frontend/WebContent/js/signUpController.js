angular.module('rentFlicks').controller(
		'SignUpController',
		[
				'$scope', '$rootScope',
				'$http', 'logging',
				function($scope, $rootScope, $http, logging) {

					$rootScope.signUpSuccess = true;
					$scope.firstName;
					$scope.lastName;
					$scope.email;
					$scope.password;
					$scope.signUp = function(firstName, lastName, email,
							password, reTypedPassword) {
						
						var req = {
							method : 'POST',
							url : 'http://localhost:8080/signUp',
							headers : {
								'Content-Type' : 'application/json'
							},
							data : {
								email : email,
								firstName : firstName,
								lastName : lastName,
								password : password
							}
						}
						if(password == reTypedPassword){
							$http(req).then(
									function(response) {
										// success callback
										if (response.status == 200) {
											$rootScope.signUpSuccess = false;
											alert("Sign Up Successful!");
											
											
										} else {
											alert("Error: "
													+ response.data.statusText);
										}
									},
									function(response) {
										// error callback
										if(typeof response.data.message === 'undefined')
											alert("Error: " + response.statusText);
										else
											alert("Error: " + response.data.message);
									});
						}
						else{
							alert('Passwords do not match!');
						}
						

					};
				} ]);