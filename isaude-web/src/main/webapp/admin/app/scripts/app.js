'use strict';

angular.module('iSaudeAdminApp', ['ngCookies', 'ngResource', 'ngSanitize'])

.config(function ($routeProvider) {
	
	$routeProvider
	
	.when('/', {templateUrl : 'views/main.html', controller : 'MainCtrl'})
	.when('/medicalinstitution',{templateUrl:'views/partial/medicalinstituitionform.tpl.html',
		controller:'MedicalinstitutionCtrl'})
	
	.otherwise({redirectTo : '/'});
	
});