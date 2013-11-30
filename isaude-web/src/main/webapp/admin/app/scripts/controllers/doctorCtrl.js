'use strict';

angular.module('iSaudeAdminApp')
  .controller('DoctorCtrl', ['$scope','MedicalSpecialityService','DoctorService', function ($scope,MedicalSpecialityService,DoctorService) {
    
    $scope.listMedicalSpecialisty = new MedicalSpecialityService();
    $scope.doctor = new DoctorService();
  }]);
