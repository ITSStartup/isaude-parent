'use strict';

angular.module('iSaudeAdminApp')
  .controller('MedicalinstitutionCtrl', ['$scope','MedicalInstitutionalService', 
  	function ($scope,MedicalInstitutionalService) {

    $scope.medicalInstitutional = new MedicalInstitutionalService();
  	$scope.listMedicalInstitutional = MedicalInstitutionalService.list();
    $scope.cnpjInvalid=false;
  		$scope.save = function(){
  			$scope.medicalInstitutional.$save(function(){
  				$scope.listMedicalInstitutional = MedicalInstitutionalService.list();
  				$scope.reset();
  			},function(response){
          if (response.status==406) {
            $scope.cnpjInvalid=true;
          };
        });
  	};//end of save function
  	
    $scope.reset = function(){
  		$scope.medicalInstitutional = new MedicalInstitutionalService();
      $scope.cnpjInvalid=false;
  	};
  
  }]);
