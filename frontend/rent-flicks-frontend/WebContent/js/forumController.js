angular.module('rentFlicks').controller(
		'ForumController',
		[ '$scope', '$compile', 'Auth', 'Email','$location', '$http', '$document',
				function($scope, $compile, Auth, Email, $location, $http, $document, $modalInstance) {
			
					$scope.movies = [];
					
					$scope.$watch(Auth.isLoggedIn, function(value, oldValue) {

						if (!value && oldValue) {
							$location.path('/home');
						}

						if (value) {
							$scope.loggedIn=true;
							// Do something when the user is connected
						}
						
						$scope.loadData = function(){
							
							var request = {
									method: 'GET',
									url: 'http://localhost:8080/movies',
									headers:{
										'Content-Type': 'application/json'
									}
							}
							$http(request).then(function (response){
								if(response.status == 200){
									$scope.movies = [];
									
									angular.forEach(response.data, function(movie){
										movie.reviewFlag = false;
										movie.noReviews = 0;
									});
									$scope.movies = response.data;
									
									
									var req = {
										method: 'GET',
										url: 'http://localhost:8080/reviews',
										headers:{
											'Content-Type': 'application/json'
										}
									}
									$http(req).then(function(resp){
										if(resp.status == 200){
											angular.forEach(resp.data, function(review){
												angular.forEach($scope.movies, function(movie){
													if(movie.movieId == review.movieId){
														if(typeof movie.reviews === 'undefined')
															movie.reviews = [];
														movie.reviews.push({
															reviewId: review.reviewId,
															review: review.review,
															rating: review.rating,
															userId: review.userId,
															userName: review.user.firstName + " " + review.user.lastName,
															userEmail: review.user.email
														});
														if(Auth.getUser().id == review.userId)
															movie.reviewFlag = true;
														movie.noReviews = movie.reviews.length;
													}
												});
												
											});
											
										}
										else{
											alert("Error: " + resp.statusText);
										}
									}, function(resp){
										if(typeof resp.data.message === 'undefined')
											alert("Error: " + resp.statusText);
										else
											alert("Error: " + resp.data.message);
									});
									
								}else{
									alert("Error: " + response.statusText );
								}
							}, function(response){
								if(typeof response.data.message === 'undefined')
									alert("Error: " + response.statusText);
								else
									alert("Error: " + response.data.message);
							});
							
						}
						
						$scope.loadData();
						
						$scope.addReview = function(movie, review, rating){
							if(parseFloat(rating) > 0 && parseFloat(rating) <= 10){
								var req = {
										method: 'POST',
										url: 'http://localhost:8080/review/add',
										headers:{
											'Content-Type': 'application/json'
										},
										data:{
											userId: Auth.getUser().id,
											movieId: movie.movieId,
											review: review,
											rating: Math.round(parseFloat(rating) * 10 ) / 10
										}
								}
								$http(req).then(function(response){
									if(response.status == 200){
										alert('Review Submitted Successfully!!');
										angular.forEach($scope.movies, function(mov){
											if(movie.movieId == mov.movieId){
												mov.reviewFlag=true;
												if(mov.noReviews == 0){
													mov.reviews = [];
													mov.noReviews = mov.noReviews + 1;
												}
												mov.reviews.push({
													reviewId: response.data.reviewId,
													review: response.data.review,
													rating: response.data.rating,
													userId: response.data.userId,
													userName: Auth.getUser().name,
													userEmail: Auth.getUser().email
												});
											}
										});
									}else{
										alert("Error: " + response.statusText );
									}
								}, function(response){
									if(typeof response.data.message === 'undefined')
										alert("Error: " + response.statusText);
									else
										alert("Error: " + response.data.message);
								});
							}
							else
								alert('Invalid rating. Rating must be a valid positive number less than 10.');
						}
			
					}, true)
					
				} 
		]);