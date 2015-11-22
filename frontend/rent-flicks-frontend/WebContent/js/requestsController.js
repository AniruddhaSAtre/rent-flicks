angular.module('rentFlicks').controller(
		'RequestsController',
		[ '$scope', '$compile', 'Auth', 'Email','$location', '$http', '$document',
				function($scope, $compile, Auth, Email, $location, $http, $document, $modalInstance) {
			
					$scope.requestData = [];
					var emailText;
					var emailSubject;
					var emailFromName;
					var emailReceiver;
					var emailReceiverName;
					
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
									 url: 'http://localhost:8080/videos/request/'+ Auth.getUser().id,
									 headers: {
									   'Content-Type': 'application/json'
									 }
									}
							$http(req).then(function(response){
								//success callback
								if (response.status == 200) {
									var isBorrowed = false;
									$scope.requestData = [];
									angular.forEach(response.data, function(item){
										var requests = item.requests;
										isBorrowed = false;
										var reqId;
										
										angular.forEach(requests, function(request){
											if(request.borrowerId == Auth.getUser().id){
												reqId = request.requestId;
												if(typeof request.checkOutDate != 'undefined'){
													isBorrowed=true;
												}
											}
											
										});
										$scope.requestData.push({
											videoId: item.videoId,
											movieId: item.movie.movieId,
											name: item.movie.title,
											image: item.movie.image,
											ownerId: item.ownerId,
											ownerEmail: item.owner.email,
											ownerName: item.owner.firstName + " " + item.owner.lastName,
											requestId: reqId,
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
						
						$scope.returnRequest = function(request, message){
						
							var reqs = {
									 method: 'POST',
									 url: 'http://localhost:8080/deny',
									 headers: {
									   'Content-Type': 'application/json'
									 },
									data:{
										requestId: request.requestId,
										videoId: request.videoId,
										borrowerId: Auth.getUser().id
									}
									}
							$http(reqs).then(function(resps){
								//success callback
								if (resps.status == 200) {
									alert("Return Requested!");
									
									if(typeof message == 'undefined')
										emailText="Hi "+ request.ownerName +",\nBorrower " + Auth.getUser().name + " has initiated a return for your movie '" + request.name +"'." + "\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi, "+ request.ownerName +",\nBorrower " + Auth.getUser().name + " has initiated a return for your movie '" + request.name +"'. Borrower says:\n" + message+".\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Return Request Initiation Notification for movie " + request.name;
									emailFromName="Return Request Initiation Notification";
									emailReceiver= request.ownerEmail;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Return Request Initiation(Owner)");
									
									if(typeof message == 'undefined')
										emailText="Hi "+ Auth.getUser().name +",\nYou have sent a return for the borrowed movie '" + request.name +"'.\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi "+ Auth.getUser().name +",\nYou have sent a return for the borrowed movie '" + request.name +"'. You messaged:\n" + message+".\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Return Request Initiation Notification for movie " + request.name;
									emailFromName="Return Request Initiation Notification";
									emailReceiver= Auth.getUser().email;
									emailReceiverName="Request Notification";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Return Request Initiation(Borrower)");
									
									
									
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
						}
					}, true)
					
				} 
		]);