angular.module('rentFlicks').service('sharedProperties', function(){
	var logged = false;
	var loggedEmail = "";
	return {
        getLogged: function () {
            return logged;
        },
        setLogged: function(value) {
            logged = value;
        },
        getEmail: function () {
            return loggedEmail;
        },
        setEmail: function(value) {
            loggedEmail = value;
        }
    };
});