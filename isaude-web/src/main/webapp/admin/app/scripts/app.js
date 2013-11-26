'use strict';

angular.module('iSaudeAdminApp', ['ngCookies', 'ngResource', 'ngSanitize'])

.config(function ($routeProvider) {
	
	$routeProvider
	
	.when('/', {templateUrl : 'views/main.html', controller : 'MainCtrl'})
	
	.when('/speciality', {templateUrl : 'views/medicalSpeciality.tpl.html', controller : 'MedicalSpecialityCtrl'})

	.otherwise({redirectTo : '/'});
	
});