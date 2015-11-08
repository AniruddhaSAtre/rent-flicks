/*angular.module('rentFlicks').controller('MoviesController',
		[ '$scope', '$compile', '$rootScope', function($scope, $compile, $rootScope) {
			
			$scope.loggedIn = $rootScope.logging.value;
			console.log('Logged in in movies: ' + $scope.loggedIn);
			console.log('inside movies controller');
		} ]);*/

angular.module('rentFlicks').controller(
		'MoviesController',
		[ '$scope', '$compile', '$rootScope', 'Auth', '$location', '$http',
				function($scope, $compile, $rootScope, Auth, $location, $http) {
					$scope.loggedIn=false;
					$scope.movieTitle;
					$scope.movieYear;
					$scope.movieActor;
					$scope.movieDirector;
					$scope.movieRating;
					$scope.moviePlot;
					$scope.movieImage;
					$scope.searchSuccess=false;
					
					var key = "9ZHs5JgrtM_LHR3x_DeKJw";
					$scope.videos = [];
					$scope.$watch(Auth.isLoggedIn, function(value, oldValue) {

						if (!value && oldValue) {
							console.log("Disconnect");
							$location.path('/home');
						}

						if (value) {
							console.log("Connect");
							$scope.loggedIn=true;
							// Do something when the user is connected
						}
						
						var req = {
								 method: 'GET',
								 url: 'http://localhost:8080/videos/user/'+ Auth.getUser().id,
								 headers: {
								   'Content-Type': 'application/json'
								 }
								}
						$http(req).then(function(response){
							//success callback
							if (response.status == 200) {
								
								$scope.videos = [];
								angular.forEach(response.data, function(item){
									$scope.videos.push({
										videoId: item.videoId,
										movieId: item.movie.movieId,
										name: item.movie.title,
										image: item.movie.image,
										ownerId: item.ownerId,
										requests: item.requests
									});
					              });
								console.log($scope.videos);
							} else {
								alert("Error: Server Returned " + response.status);
							}
						}, function(response){
							//error callback
							alert("Error: Server returned " + response.status);
						});
						
						
						$scope.search = function(title){
							alert(title);
							
							var req = {
									 method: 'GET',
									 url: 'http://www.omdbapi.com/?t='+title+'&y=&plot=full&r=json',
									 headers: {
									   'Content-Type': 'application/json'
									 }
									}
							$http(req).then(function(response){
								//success callback
								if (response.status == 200) {
									$scope.searchSuccess=true;
									$scope.movieTitle = response.data.Title;
									$scope.movieYear = response.data.Year;
									$scope.movieDirector= response.data.Director;
									$scope.movieActor = response.data.Actors;
									$scope.moviePlot = response.data.Plot;
									$scope.movieImage = response.data.Poster;
									$scope.movieRating = response.data.imdbRating;
								} else {
									alert("Error: Could not find the movie");
								}
							}, function(response){
								//error callback
								alert("Error: Third party APIs are currently not reachable. ");
							});
							
						}
						
						$scope.addMovie = function(){
							console.log($scope.moviePlot);
							console.log($scope.movieActor);
							var r = {
									 method: 'POST',
									 url: 'http://localhost:8080/add',
									 headers: {
									   'Content-Type': 'application/json'
									 },
									data:{
										title: $scope.movieTitle,
										actor: $scope.movieActor,
										director: $scope.movieDirector,
										year: parseInt($scope.movieYear),
										plot:$scope.moviePlot,
										criticRating:parseFloat($scope.movieRating),
										image: $scope.movieImage
									}
								}
							alert(r.data.title + ":"+ r.data.actor);
							alert(r.data.year + ":"+ r.data.director);
							alert(r.data.criticRating + ":"+ r.data.plot);
							alert(r.data.image);
							$http(r).then(function(response){
								//success callback
								if (response.status == 200) {
									console.log('Response Add: '+ response.data);
									var videoReq = {
											method: 'POST',
											 url: 'http://localhost:8080/video/add',
											 headers: {
											   'Content-Type': 'application/json'
											 },
											data:{
												ownerId: Auth.getUser().id,
												movieId: response.data.movieId
											}
									}
									
									$http(videoReq).then(function(response){
										//success callback
										if(response.data.status ==200){
											alert('video is added');
										}
										else{
											alert("Error: Could not add the video!");
										}
									}, function(response){
										//error callback
										alert("Error: Soomething went wrong while calling service");
									});
									
									
								} else {
									alert("Error: Could not find the movie");
								}
							}, function(response){
								//error callback
								alert("Error: Third party APIs are currently not reachable. ");
							});
						}
						
						$scope.accept = function(video, request, message){
							//alert(video.name + ": " + request.borrower.email + ":" + message);
							var d = new Date();
							var dateStr = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
							//alert(dateStr + " :R- " + request.requestId + ":V- " + request.videoId + ": B- " + request.borrowerId);
							var req = {
									 method: 'POST',
									 url: 'http://localhost:8080/accept',
									 headers: {
									   'Content-Type': 'application/json'
									 },
									data:{
										requestId: request.requestId,
										videoId: request.videoId,
										borrowerId: request.borrowerId,
										checkOutDate: dateStr
									}
									}
							$http(req).then(function(response){
								//success callback
								if (response.status == 200) {
									alert("Request Accepted!");
									
									var mailJSON ={
									        "key": key,
									        "message": {
									          "html": "",
									          "text": "Hi, "+ Auth.getUser().name +"\n You have accepted borrow request for your movie '" + video.name +"' from " + request.borrower.email + "\n\nRegards, \n\nTeam Rent-Flicks",
									          "subject": "Borrow Request Acceptance Notification for movie " + video.name,
									          "from_email": "noreply@rentflicks.com",
									          "from_name": "Borrow Request Acceptance Notification",
									          "to": [
									            {
									              "email": Auth.getUser().email,
									              "name": "Request Notification",
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
										          "text": "Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \n Your borrow reques for movie '" + video.name +"' has been accepted by the owner.\n\nRegards, \n\nTeam Rent-Flicks",
										          "subject": "Borrow Request Acceptance Notification for movie " + video.name,
										          "from_email": "noreply@rentflicks.com",
										          "to": [
										            {
										              "email": Auth.getUser().email,
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
									    
									
								} else {
									alert("Error: Server Returned " + response.status);
								}
							}, function(response){
								//error callback
								alert("Error: Server returned " + response.status);
							});
						};
						
						$scope.deny = function(video, request, message){
							//alert(video.name + ": " + request.borrower.email + ":" + message);
							var reqs = {
									 method: 'POST',
									 url: 'http://localhost:8080/deny',
									 headers: {
									   'Content-Type': 'application/json'
									 },
									data:{
										requestId: request.requestId,
										videoId: request.videoId,
										borrowerId: request.borrowerId
									}
									}
							$http(reqs).then(function(response){
								//success callback
								if (response.status == 200) {
									alert("Request Denied!");
									
									var mailJSON ={
									        "key": key,
									        "message": {
									          "html": "",
									          "text": "Hi, "+ Auth.getUser().name +"\n You have denied borrow request for your movie '" + video.name +"' from " + request.borrower.email + "\n\nRegards, \n\nTeam Rent-Flicks",
									          "subject": "Borrow Request Denial Notification for movie " + video.name,
									          "from_email": "noreply@rentflicks.com",
									          "to": [
									            {
									              "email": Auth.getUser().email,
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
										          "text": "Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \n Your borrow reques for movie '" + video.name +"' has been denied by the owner.\n\nRegards, \n\nTeam Rent-Flicks",
										          "subject": "Borrow Request Acceptance Notification for movie " + video.name,
										          "from_email": "noreply@rentflicks.com",
										          "to": [
										            {
										              "email": Auth.getUser().email,
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
									    
									
								} else {
									alert("Error: Server Returned " + response.status);
								}
							}, function(response){
								//error callback
								alert("Error: Server returned " + response.status);
							});
							
							
						};
						
					}, true)
				} ]);