'use strict';

angular.module('iSaudeAdminApp')
  .factory('MedicalInstitutionalService', function ($resource) {
     return $resource('../../api/medicalinstitution/:id',{},{
    create:{
      method:'POST'
    },
    list:{
      method:'GET',
      isArray:true
    },
    update:{
      method:'PUT'
    },
    remove:{
      method:'DELETE'
    }
  }
  );
});
