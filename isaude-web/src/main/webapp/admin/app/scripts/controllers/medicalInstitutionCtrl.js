'use strict';

angular.module('iSaudeAdminApp')
  .controller('MedicalinstitutionCtrl', ['$scope','MedicalInstitutionalService', 
  	function ($scope,MedicalInstitutionalService) {

    $scope.medicalInstitutional = new MedicalInstitutionalService();
  	$scope.listMedicalInstitutional = MedicalInstitutionalService.list();
    $scope.cnpjInvalid=false;
    $scope.dataSuccess = false;

    //save
  		$scope.save = function(){
        if($scope.medicalInstitutional.id > 0){
          $scope.update();
        }else{
          $scope.medicalInstitutional.$create(function(){
          $scope.listMedicalInstitutional = MedicalInstitutionalService.list();
          $scope.reset();
          $scope.dataSuccess = true;
        }, 
         function(err){
              var errors = err.data;              
              angular.forEach(errors, function(error){
                if (error.message=='CNPJ_EXISTS') {
                  $scope.cnpjInvalid=true;
                };
              });
          
        });  
        }
  			
  	};//end of save 
  	

    $scope.edit = function(med){
      $scope.medicalInstitutional = med;
    };

   //update
    $scope.update = function(){
      $scope.medicalInstitutional.$update(function(){
        $scope.listMedicalInstitutional = MedicalInstitutionalService.list();
        $scope.reset();

      });
    };//end of update

    //remove 
    $scope.remove = function(medicalInstitutional){
     $scope.medicalInstitutionalToRemove = medicalInstitutional;         
      };

    $scope.doRemove = function(){
      $scope.medicalInstitutionalToRemove.$remove({id : $scope.medicalInstitutionalToRemove.id},function(res){
        $scope.listMedicalInstitutional = MedicalInstitutionalService.list();
        $scope.medicalInstitutional = new MedicalInstitutionalService();  
         
      });   
    }

    $scope.reset = function(){
  		$scope.medicalInstitutional = new MedicalInstitutionalService();
      $scope.cnpjInvalid=false;
       $scope.dataSuccess = false;
  	};
  
  }]);
