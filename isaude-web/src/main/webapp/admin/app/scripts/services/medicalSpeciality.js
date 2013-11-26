'use strict';

angular.module('iSaudeAdminApp')

.factory('MedicalSpecialityService', ['$resource', function($resource) {

	return $resource('../../api/speciality/:id', {
		id : '@id'
	}, {
		list: {method: 'GET', isArray : true},
		update: {method: 'PUT'},
		create: {method: 'POST'},
		remove: {method: 'DELETE'}
	});

}]);