'use strict';

angular.module('iSaudeApp', ['ngCookies', 'ngResource', 'ngSanitize'])

.config(function ($routeProvider) {
	$routeProvider
		.when('/', {templateUrl: 'views/home.html', controller: 'MainCtrl'})
		.when('/main', {templateUrl: 'views/main.html', controller: 'MainCtrl'})
		.otherwise({redirectTo: '/'});
});