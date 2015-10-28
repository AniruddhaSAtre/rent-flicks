(function() {
	var app = angular.module('rentFlicks', ["ui.bootstrap.modal"]);
	var flicks = [
			{
				name : "Interstellar",
				year : 2014,
				image : "http://ia.media-imdb.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX300.jpg",
				plot : "In the near future, Earth has been devastated by drought and famine, causing a scarcity in food and extreme changes in climate. When humanity is facing extinction, a mysterious rip in the space-time continuum is discovered, giving mankind the opportunity to widen its lifespan. A group of explorers must travel beyond our solar system in search of a planet that can sustain life. The crew of the Endurance are required to think bigger and go further than any human in history as they embark on an interstellar voyage into the unknown. Coop, the pilot of the Endurance, must decide between seeing his children again and the future of the human race.",
				actor : "Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow",
				director : "Christopher Nolan"
			},
			{
				name : "Gravity",
				year : 2014,
				image : "http://ia.media-imdb.com/images/M/MV5BNjE5MzYwMzYxMF5BMl5BanBnXkFtZTcwOTk4MTk0OQ@@._V1_SX300.jpg",
				plot : "A medical engineer and an astronaut work together to survive after a catastrophe destroys their shuttle and leaves them adrift in orbit.",
				actor : "Sandra Bullock, George Clooney, Ed Harris, Orto Ignatiussen",
				director : "Alfonso Cuarón"
			},
			{
				name : "Martian",
				year : 2015,
				image : "http://ia.media-imdb.com/images/M/MV5BMTc2MTQ3MDA1Nl5BMl5BanBnXkFtZTgwODA3OTI4NjE@._V1_SX300.jpg",
				plot : "During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive",
				actor : "Matt Damon, Jessica Chastain, Kristen Wiig, Jeff Daniels",
				director : "Ridley Scott"
			},
			{
				name : "Inception",
				year : 2010,
				image : "http://ia.media-imdb.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg",
				plot : "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible-inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.",
				actor : "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page, Tom Hardy",
				director : "Christopher Nolan"
			},
			{
				name : "Avatar",
				year : 2009,
				image : "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg",
				plot : "When his brother is killed in a robbery, paraplegic Marine Jake Sully decides to take his place in a mission on the distant world of Pandora. There he learns of greedy corporate figurehead Parker Selfridge's intentions of driving off the native humanoid \"Na'vi\" in order to mine for the precious material scattered throughout their rich woodland. In exchange for the spinal surgery that will fix his legs, Jake gathers intel for the cooperating military unit spearheaded by gung-ho Colonel Quaritch, while simultaneously attempting to infiltrate the Na'vi people with the use of an \"avatar\" identity. While Jake begins to bond with the native tribe and quickly falls in love with the beautiful alien Neytiri, the restless Colonel moves forward with his ruthless extermination tactics, forcing the soldier to take a stand - and fight back in an epic battle for the fate of Pandora.",
				actor : "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
				director : "James Cameron"
			} ]
	app.controller('CatalogController', [ '$scope', function($scope) {
		$scope.flicks = flicks;
		$scope.isLoggedIn = false;
		$scope.searchFilter = '$';
		$scope.clear = function(){
		    flick = {name:"",actor : "", plot : "", year : 0, director : ""};
		$scope.index = 0;

	    };
		// $scope.searchCriteriaVal = "$";
		/*
		 * $scope.searchCriteriaValue = function(value) {
		 * $scope.searchCriteriaVal = value; console.log(value);
		 * console.log('scope'); console.log($scope.searchCriteriaVal); }
		 */
	} ]);

	app.controller('SearchController', [ '$scope', function($scope) {

	} ]);

	/*
	 * app.controller("PanelController", function(){ this.tab=1; this.selectTab =
	 * function(tab){ this.tab = tab; }; this.isSelected= function(tab){ return
	 * this.tab==tab; } });
	 */
})();
