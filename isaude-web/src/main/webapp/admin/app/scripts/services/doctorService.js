'use strict';

angular.module('iSaudeAdminApp')
  .factory('DoctorService', function ($resource) {
     return $resource('../../api/doctor/:id',{},{
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
