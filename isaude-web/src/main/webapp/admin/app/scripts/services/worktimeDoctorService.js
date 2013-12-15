'use strict';

angular.module('iSaudeAdminApp')

.factory('WorktimeDoctorService', ['$resource', function($resource) {

    return $resource('../../api/worktime/:id:idMedico/:idInstituicao', {
        id: "@id",
        idMedico: "@idMedico",
        idInstituicao: "@idInstituicao"
    }, {
        list: {method: 'GET', isArray: true},
        update: {method: 'PUT'},
        create: {method: 'POST'},
        remove: {method: 'DELETE'}
    });

}]);