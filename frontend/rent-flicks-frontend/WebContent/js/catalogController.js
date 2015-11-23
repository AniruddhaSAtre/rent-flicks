angular.module('rentFlicks').controller('CatalogController', ['$scope', '$compile', '$rootScope', 'Auth', 'Email', '$location', '$http', '$timeout', function ($scope, $compile, $rootScope, Auth, Email, $location, $http, $timeout) {
	var emailText;
	var mailSubject;
	var emailFromName;
	var emailReceiver;
	var emailReceiverName;
	
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
					if(item.owner.userId == Auth.getUser().id){
						item['isOwner'] = true;
					}
					else{
						item['isOwner'] = false;
					}
					var requests = item.requests;
					item['isBorrowed'] = false;
					
					if(requests.length!=0){
						angular.forEach(requests, function(request){
							if(typeof request.checkOutDate != 'undefined'){
								item['isBorrowed'] =  true;
							}
							
						});
					}
						
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
						isOwner: item.isOwner,
						isBorrowed: item.isBorrowed,
						borrowRequestSuccess: '',
						borrowRequestError: '',
						borrowMessage: ''
					});
	              });
				console.log($scope.flicks);
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
									if(typeof flick.borrowMessage == 'undefined')
										emailText="Hi,\n" + Auth.getUser().name + " has requested to borrow your movie '" + flick.name +"'.\n\nPlase log in to acccept this request. \n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText="Hi,\n" + Auth.getUser().name + " has requested to borrow your movie '" + flick.name +"'.\nUser says: \n" + flick.borrowMessage + "\n\nPlase log in to acccept this request. \n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request for movie " + flick.name;
									emailFromName="New Borrow Request";
									emailReceiver=flick.ownerEmail;
									emailReceiverName="New Request";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request(Owner)");
									
									if(typeof flick.borrowMessage == 'undefined')
										emailText= "Hi "+ Auth.getUser().name +",\n" + "Your request to borrow movie '" + flick.name +"' has been sent to owner.\n\nRegards, \n\nTeam Rent-Flicks";
									else
										emailText= "Hi "+ Auth.getUser().name +",\n" + "Your request to borrow movie '" + flick.name +"' has been sent to owner with this message: \n" + flick.borrowMessage + "\n\nRegards, \n\nTeam Rent-Flicks";
									emailSubject="Borrow Request for movie " + flick.name;
									emailFromName="Borrow Request";
									emailReceiver = Auth.getUser().email;
									emailReceiverName ="New Request";
									Email.sendEmail(emailText, emailSubject, emailFromName, emailReceiver, emailReceiverName, "Borrow Request(Borrower)");	
									
								} else {
									alert("Error: Server Returned "
											+ resp.statusText);
								}
							},
							function(resp) {
								// error callback
								if(typeof resp.data.message === 'undefined')
									alert("Error: " + resp.statusText);
								else
									alert("Error: " + resp.data.message);
								console.log(resp);
							});

				};
	    		
	    	}
	  }, true)}]);

function sendEmail(){
	
}