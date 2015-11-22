angular.module('rentFlicks').controller(
		'MoviesController',
		[ '$scope', '$compile', 'Auth', 'Email','$location', '$http', '$document',
				function($scope, $compile, Auth, Email, $location, $http, $document, $modalInstance) {
			
			var emailText;
			var mailSubject;
			var emailFromName;
			var emailReceiver;
			var emailReceiverName;
			
					$scope.loggedIn=false;
					$scope.movieTitle;
					$scope.movieYear;
					$scope.movieActor;
					$scope.movieDirector;
					$scope.movieRating;
					$scope.moviePlot;
					$scope.movieImage;
					$scope.searchSuccess=false;
					$scope.modalVal;
					$scope.modalIndex;
					
					$scope.videos = [];
					$scope.$watch(Auth.isLoggedIn, function(value, oldValue) {

						if (!value && oldValue) {
							$location.path('/home');
						}

						if (value) {
							$scope.loggedIn=true;
							// Do something when the user is connected
						}
						
						$scope.loadData = function(){
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
										var requests = item.requests;
										var isBorrowed = false;
										angular.forEach(requests, function(request){
											if(typeof request.checkOutDate != 'undefined'){
												isBorrowed = true;
												request['isBorrowed'] = true;
												console.log(item.movie.title);
												console.log(request.borrower.email);
											}
											else{
												request['isBorrowed'] = false;
											}
										});
										$scope.videos.push({
											videoId: item.videoId,
											movieId: item.movie.movieId,
											name: item.movie.title,
											image: item.movie.image,
											ownerId: item.ownerId,
											requests: item.requests,
											isBorrowed: isBorrowed
										});
						              });
								} else {
									alert("Error: Server Returned " + response.statusText);
								}
							}, function(response){
								//error callback
								if(typeof response.data.message === 'undefined')
									alert("Error: " + response.statusText);
								else
									alert("Error: " + response.data.message);
							});
							
						}
						
						
						$scope.loadData();
						
						$scope.openModal = function(index){
							$scope.modalVal = 'requestsModal' + index;
							$scope.modalIndex = index;
							$scope.modalVal = true;
						}
						
						$scope.cancel = function (index) {
							
							 $scope.modalVal = false;
						 };
						
						$scope.search = function(title){
							
							
							var req = {
									 method: 'GET',
									 url: 'http://www.omdbapi.com/?t='+title+'&y=&plot=full&r=json',
									 headers: {
									   'Content-Type': 'application/json'
									 }
									}
							$http(req).then(function(response){
								//success callback
								
								if (response.status == 200 && !("Error" in response.data)) {
									
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
									$scope.searchSuccess=false;
									$scope.movieTitle = '';
									$scope.movieYear = '';
									$scope.movieDirector= '';
									$scope.movieActor = '';
									$scope.moviePlot = '';
									$scope.movieImage = '';
									$scope.movieRating = '';
								}
							}, function(response){
								//error callback
								alert("Error: Third party APIs are currently not reachable. ");
							});
							
						}
						
						$scope.addVideo = function(movieId){
							var videoReq = {
									method: 'POST',
									 url: 'http://localhost:8080/video/add',
									 headers: {
									   'Content-Type': 'application/json'
									 },
									data:{
										ownerId: Auth.getUser().id,
										movieId: movieId
									}
							}
							
							$http(videoReq).then(function(responseOne){
								//success callback
								
								if(responseOne.status ==200){
									alert('video is added');
									$scope.videos.push({
										videoId: responseOne.data.videoId,
										movieId: responseOne.data.movieId,
										name: $scope.movieTitle,
										image: $scope.movieImage,
										ownerId: responseOne.data.ownerId,
										requests: [],
										isBorrowed: false
									});
								}
								else{
									alert("Error: Could not add the video, error description: " + responseOne.statusText);
								}
							}, function(responseOne){
								//error callback
								if(typeof responseOne.data.message === 'undefined')
									alert("Error: Could not add the video, error description: " + responseOne.statusText);
								else
									alert("Error: Could not add the video, error description: " + responseOne.data.message);
							});
						}
						
						$scope.addNewMovie = function(){
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
							$http(r).then(function(response){
								//success callback
								
								if (response.status == 200) {
									console.log('New Movie added is: ' + response.data.movieId );
									$scope.addVideo(response.data.movieId);
									
								}else{
									alert('Error: Could not add new movie, error description: ' + response.statusText);
									}
								}, function(response){
									if(typeof response.data.message === 'undefined')
										alert("Error: Could not add new movie, error description: " + response.statusText);
									else
										alert("Error: Could not add new movie, error description: " + response.data.message);
						});
					}
						
						
						$scope.addMovie = function(){
							var newMovieFlag = false;
							var rq = {
									method: 'GET',
									url: 'http://localhost:8080/movies/' + $scope.movieTitle,
									headers: {
										'Content-Type': 'application/json'
									}
							}
							$http(rq).then(function(rsp){
								if(rsp.status==200){
									console.log('Found existing movie');
									if(rsp.data.length != 0){
										$scope.addVideo(rsp.data[0].movieId);
									}
									else{
										$scope.addNewMovie();
									}
								}
								else{
									consle.log('Did not find a new movie, adding a new one..');
									$scope.addNewMovie();
								}
							}, function(rsp){
								console.log('Error in getting movie, now adding a new one');
								$scope.addNewMovie();
							});				
								
						}
						
						
						$scope.accept = function(video, request, message, index){
							
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
									video['isBorrowed'] = true;
									
									angular.forEach($scope.videos, function(video){
										video['isBorrowed'] = true;
										angular.forEach(video.requests, function(req){
											
											if(req.borrower.userId == request.borrower.userId){
												req['isBorrowed'] = true;
											}
											
										});
									});
									$scope.cancel($scope.modalIndex);
									$scope.loadData();
									if(typeof message == 'undefined')
										emailText="Hi, "+ Auth.getUser().name +"\nYou have accepted borrow request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName + ".\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi, "+ Auth.getUser().name +"\nYou have accepted borrow request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName + ". You said in reply to borrower:\n"+message+"\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request Acceptance Notification for movie " + video.name;
									emailFromName="Borrow Request Acceptance Notification";
									emailReceiver=Auth.getUser().email;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request Acceptance(Owner)");
									
									if(typeof message == 'undefined')
										emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nYour borrow request for movie '" + video.name +"' has been accepted by the owner.\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nYour borrow request for movie '" + video.name +"' has been accepted by the owner.Owner says: \n\n"+ message +"\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request Acceptance Notification for movie " + video.name;
									emailFromName="Borrow Request Acceptance Notification";
									emailReceiver=request.borrower.email;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request Acceptance(Borrower)");
									
								} else {
									alert("Error: " + response.statusText);
								}
							}, function(response){
								//error callback
								if(typeof response.data.message === 'undefined')
									alert("Error: " + response.statusText);
								else
									alert("Error: " + response.data.message);
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
							$http(reqs).then(function(resps){
								//success callback
								if (resps.status == 200) {
									alert("Request Denied!");
									
									if(typeof message == 'undefined')
										emailText="Hi, "+ Auth.getUser().name +"\nYou have denied borrow request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName  + ".\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi, "+ Auth.getUser().name +"\nYou have denied borrow request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName  + ", saying: \n"+ message +"\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request Denial Notification for movie " + video.name;
									emailFromName="Borrow Request Denial Notification";
									emailReceiver=Auth.getUser().email;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request Denial(Owner)");
									
									if(typeof message == 'undefined')
										emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nYour borrow request for movie '" + video.name +"' has been denied by the owner.\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nYour borrow request for movie '" + video.name +"' has been denied by the owner. Owner says:\n"+ message+"\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request Denial Notification for movie " + video.name;
									emailFromName="Borrow Request Denial Notification";
									emailReceiver=request.borrower.email;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request Denial(Borrower)");
									    
									
								} else {
									alert("Error: Server Returned " + resps.statusText);
								}
							}, function(resps){
								//error callback
								if(typeof response.data.message === 'undefined')
									alert("Error: " + resps.statusText);
								else
									alert("Error: " + resps.data.message);
							});
							
							
						};
						
					}, true)
					
					$scope.askReturn = function(video, request, message){
						if(typeof message == 'undefined')
							emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nOwner has requested a return for your borrow request for movie '" + video.name +"'.\n\nRegards, \n\nTeam Rent-Flicks";
						else
							emailText="Hi "+ request.borrower.firstName + " " + request.borrower.lastName +", \nOwner has requested a return for your borrow request for movie '" + video.name +"'. Owner says:\n"+ message+"\n\nRegards, \n\nTeam Rent-Flicks";
						emailSubject="Return Request Notification for movie " + video.name;
						emailFromName="Return Request Notification";
						emailReceiver=request.borrower.email;
						emailReceiverName="Request Notification";
						Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Return Request (Borrower)");
						
						if(typeof message == 'undefined')
							emailText="Hi, "+ Auth.getUser().name +"\nYou have sent a return request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName  + ".\n\nRegards, \n\nTeam Rent-Flicks";
						else
							emailText="Hi, "+ Auth.getUser().name +"\nYou have sent a return request for your movie '" + video.name +"' from " + request.borrower.firstName + " " + request.borrower.lastName  + ", saying: \n"+ message +"\n\nRegards, \n\nTeam Rent-Flicks";
						emailSubject="Return Request Notification for movie " + video.name;
						emailFromName="Return Request Notification";
						emailReceiver=Auth.getUser().email;
						emailReceiverName="Request Notification";
						Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Return Request(Owner)");
						
						alert('Reminder for return is emailed to the borrower!');
					}
				} 
		]);