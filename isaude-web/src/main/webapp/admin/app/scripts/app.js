'use strict';

angular.module('iSaudeAdminApp', ['ngCookies', 'ngResource', 'ngSanitize'])

.config(function ($routeProvider) {
	
	$routeProvider
	
<<<<<<< HEAD
	.when('/', {
		templateUrl : 'views/main.html', 
		controller : 'MainCtrl'
	})
	
	.when('/medicalinstitution',{
		templateUrl:'views/partial/medicalinstituitionform.tpl.html', 
		controller:'MedicalinstitutionCtrl'
	})
	
	.when('/speciality', {
		templateUrl : 'views/partial/medicalSpeciality.tpl.html', 
		controller : 'MedicalSpecialityCtrl'
	})
=======
	.when('/', {templateUrl : 'views/main.html', controller : 'MainCtrl'})
	.when('/medicalinstitution',{templateUrl:'views/partial/medicalinstituitionform.tpl.html',
		controller:'MedicalinstitutionCtrl'})	
	.when('/speciality', {templateUrl : 'views/medicalSpeciality.tpl.html', controller : 'MedicalSpecialityCtrl'})
	.when('/doctor', {templateUrl : 'views/partial/doctorform.tpl.html', controller : 'DoctorCtrl'})
>>>>>>> 22846ae65bffdb48c58c1cacb430019ead564549

	.otherwise({redirectTo : '/'});
	
});