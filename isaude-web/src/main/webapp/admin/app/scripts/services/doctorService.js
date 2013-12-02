'use strict';

angular.module('iSaudeAdminApp')

.factory('DoctorService', ['$resource', function($resource) {

  return $resource('../../api/doctor/:id', { }, {
    list: {method: 'GET', isArray : true},
    update: {method: 'PUT'},
    create: {method: 'POST'},
    remove: {method: 'DELETE'}
  });

}]);