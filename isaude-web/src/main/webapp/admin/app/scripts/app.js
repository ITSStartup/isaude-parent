'use strict';

angular.module('iSaudeAdminApp', ['ngCookies', 'ngResource', 'ngSanitize', 'ui.mask'])

.config(function ($routeProvider) {
	
	$routeProvider
	
	.when('/', {
		templateUrl : 'views/main.html', 
		controller : 'MainCtrl'
	})
	
	.when('/medicalinstitution',{
		templateUrl:'views/partial/medicalinstituitionform.tpl.html', 
		controller:'MedicalinstitutionCtrl'
	})
	
	.when('/medicalspeciality', {
		templateUrl : 'views/partial/medicalspecialityform.tpl.html', 
		controller : 'MedicalSpecialityCtrl'
	})

	.when('/doctor', {
		templateUrl : 'views/partial/doctorform.tpl.html', 
		controller : 'DoctorCtrl'
	})

        .when('/worktimeDoctor', {
            templateUrl: 'views/partial/worktimedoctorform.html',
            controller: 'WorktimeDoctorCtrl'
        })
        
        .otherwise({redirectTo: '/'});
	
});