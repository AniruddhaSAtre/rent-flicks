(function() {
	var app = angular.module('rentFlicks', ["ui.bootstrap.modal"]);

	var flicks = [
			{
				name : "The Da Vinci Code",
				year : 2012,
				image : "http://ia.media-imdb.com/images/M/MV5BMjIxMjQyMTc3Nl5BMl5BanBnXkFtZTcwMTA1MDUzMw@@._V1_SX300.jpg",
				plot : "A murder inside the Louvre and clues in Da Vinci paintings lead to the discovery of a religious mystery protected by a secret society for two thousand years -- which could shake the foundations of Christianity.",
				actor : "Tom Hanks, Audrey Tautou, Ian McKellen, Jean Reno",
				director : "Ron Howard"
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
				name : "The Da Vinci Code",
				year : 2012,
				image : "http://ia.media-imdb.com/images/M/MV5BMjIxMjQyMTc3Nl5BMl5BanBnXkFtZTcwMTA1MDUzMw@@._V1_SX300.jpg",
				plot : "A murder inside the Louvre and clues in Da Vinci paintings lead to the discovery of a religious mystery protected by a secret society for two thousand years -- which could shake the foundations of Christianity.",
				actor : "Tom Hanks, Audrey Tautou, Ian McKellen, Jean Reno",
				director : "Ron Howard"
			},
			{
				name : "Gravity",
				year : 2014,
				image : "http://ia.media-imdb.com/images/M/MV5BNjE5MzYwMzYxMF5BMl5BanBnXkFtZTcwOTk4MTk0OQ@@._V1_SX300.jpg",
				plot : "A medical engineer and an astronaut work together to survive after a catastrophe destroys their shuttle and leaves them adrift in orbit.",
				actor : "Sandra Bullock, George Clooney, Ed Harris, Orto Ignatiussen",
				director : "Alfonso Cuarón"
			} ]
	app.controller('CatalogController', [ '$scope', function($scope) {
		$scope.flicks = flicks;
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
