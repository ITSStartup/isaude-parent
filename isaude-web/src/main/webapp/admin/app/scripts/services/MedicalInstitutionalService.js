'use strict';

angular.module('iSaudeAdminApp')
  .factory('MedicalInstitutionalService', function ($resource) {
     return $resource('../../api/medicalinstitution/:id',{},{
    save:{
      method:'POST'
    },
    list:{
      method:'GET',
      isArray:true
    }
  });
});
