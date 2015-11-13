/*angular.module('rentFlicks').controller('CatalogController',
		[ '$scope', '$compile', '$http', function($scope, $compile, $http) {
			console.log('inside home controller');
			
			var req = {
				 method: 'GET',
				 url: 'http://localhost:8080/videos',
				 headers: {
				   'Content-Type': 'application/json'
				 }
				}
		$http(req).then(function(response){
			//success callback
			if (response.status == 200) {
				$scope.flicks = [];
				angular.forEach(response.data, function(item){
					$scope.flicks.push({
						year: item.movie.year,
						plot: item.movie.plot,
						actor: item.movie.actor,
						name: item.movie.title,
						rating: item.movie.criticRating,
						image: item.movie.image,
						director: item.movie.director,
						owner: item.owner.firstName + ' ' + item.owner.lastName,
						ownerEmail: item.owner.email,
						ownerId: item.owner.userId,
						videoId: item.videoId,
						loggedIn: $scope.loggedIn
					});
	              });
				console.log($scope.flicks);
			} else {
				alert("Error: Server Returned " + response.status);
			}
		}, function(response){
			//error callback
			alert("Error: Server returned " + response.status);
		});
			
		} ]);*/
angular.module('rentFlicks').controller('CatalogController', ['$scope', '$compile', '$rootScope', 'Auth', '$location', '$http', '$timeout', function ($scope, $compile, $rootScope, Auth, $location, $http, $timeout) {

	var key = "9ZHs5JgrtM_LHR3x_DeKJw";
	
	var mandrillObj = new mandrill.Mandrill(key);
	$scope.loggedIn = false;
	$scope.flicks = [];
	$scope.message = '';
	  $scope.$watch(Auth.isLoggedIn, function (value, oldValue) {

	    if(!value && oldValue) {
	      $scope.loggedIn= false;
	      //$location.path('/home');
	    }
		

	    if(value) {
	      $scope.loggedIn= true;
	      //Do something when the user is connected
	    }
	    var logged;
	    if($scope.loggedIn){ 
	    	logged= 'true'; 
	    } 
	    else{
	    	logged= 'false';
	    }
	    //console.log(logged)
	    var req = {
				 method: 'GET',
				 url: 'http://localhost:8080/videos',
				 headers: {
				   'Content-Type': 'application/json'
				 }
				}
		$http(req).then(function(response){
			//success callback
			if (response.status == 200) {
				$scope.flicks = [];
				angular.forEach(response.data, function(item){
					$scope.flicks.push({
						year: item.movie.year,
						plot: item.movie.plot,
						actor: item.movie.actor,
						name: item.movie.title,
						rating: item.movie.criticRating,
						image: item.movie.image,
						director: item.movie.director,
						owner: item.owner.firstName + ' ' + item.owner.lastName,
						ownerEmail: item.owner.email,
						ownerId: item.owner.userId,
						videoId: item.videoId,
						loggedIn: $scope.loggedIn,
						borrowRequestSuccess: '',
						borrowRequestError: '',
						borrowMessage: ''
					});
	              });
			} else {
				alert("Error: Server Returned " + response.status);
			}
		}, function(response){
			//error callback
			alert("Error: Server returned " + response.status);
		});
	    
	    $scope.sendBorrowRequest = function(flick, index){
	    	var userId = Auth.getUser().id;
	    	//alert(index);
	    	if(flick.ownerId == userId){
	    		flick.borrowRequestError = "You cannot borrow your movie!:)";
	    	}
	    	else{
	 
	    		var request = {
						method : 'POST',
						url : 'http://localhost:8080/borrow',
						headers : {
							'Content-Type' : 'application/json'
						},
						data : {
							videoId : flick.videoId,
							borrowerId : Auth.getUser().id
						}
					}
					$http(request).then(
							function(resp) {
								alert("Borrow Request Sent successfully!");
								// success callback
								if (resp.status == 200) {
									
									
									var mailJSON ={
									        "key": key,
									        "message": {
									          "html": "",
									          "text": "Hi,\n" + Auth.getUser().name + " has requested to borrow your movie '" + flick.name +"'.\nUser says: \n" + flick.borrowMessage + "\n\nPlase log in to acccept this request. \n\nRegards, \n\nTeam Rent-Flicks",
									          "subject": "Borrow Request for movie " + flick.name,
									          "from_email": "noreply@rentflicks.com",
									          "from_name": "New Borrow Request",
									          "to": [
									            {
									              "email": flick.ownerEmail,
									              "name": "New Request",
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
									    var apiURL = "https://mandrillapp.com/api/1.0/messages/send.json";
									    $http.post(apiURL, mailJSON).
									      success(function(data, status, headers, config) {
									        console.log('borrow email request status: ' + status);
									        /*$timeout(function() {
									            var el = document.getElementById('cancel' + index);
									            angular.element(el).triggerHandler('data-dismiss');
									        }, 0);*/
									      }).error(function(data, status, headers, config) {
									        alert('error sending email, status: ' + status);
									      });
									    mailJSON ={
										        "key": key,
										        "message": {
										          "html": "",
										          "text": "Hi "+ Auth.getUser().name +",\n" + "Your request to borrow movie '" + flick.name +"' has been sent to owner with this message: \n" + flick.borrowMessage + "\n\nRegards, \n\nTeam Rent-Flicks",
										          "subject": "Borrow Request for movie " + flick.name,
										          "from_email": "noreply@rentflicks.com",
										          "from_name": "Borrow Request",
										          "to": [
										            {
										              "email": Auth.getUser().email,
										              "name": "New Request",
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
									        console.log('borrow email request status for borrower: ' + status);
									        /*$timeout(function() {
									            var el = document.getElementById('cancel' + index);
									            angular.element(el).triggerHandler('data-dismiss');
									        }, 0);*/
									      }).error(function(data, status, headers, config) {
									        alert('error sending email, status: ' + status);
									      });
									
									
								} else {
									alert("Error: Server Returned "
											+ resp.status);
								}
							},
							function(resp) {
								// error callback
								alert("Error: Server returned "
										+ resp.status);
								console.log(resp);
							});

				};
	    		
	    	}
	  }, true)}]);