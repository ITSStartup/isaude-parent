'use strict';

angular.module('iSaudeAdminApp')
  .controller('MedicalinstitutionCtrl', ['$scope','MedicalInstitutionalService', 
  	function ($scope,MedicalInstitutionalService) {

    $scope.medicalInstitutional = new MedicalInstitutionalService();
  	$scope.listMedicalInstitutional = MedicalInstitutionalService.list();
    $scope.cnpjInvalid=false;
    $scope.razaoSocialInvalid=false;
    $scope.dataSuccess = false;
    $scope.editCnpj = true;

    //save
  		$scope.save = function(){
        var id = 0
        if($scope.medicalInstitutional.id > id){
          $scope.update();
        }else{
          $scope.medicalInstitutional.$create(function(){
          $scope.listMedicalInstitutional = MedicalInstitutionalService.list();
          $scope.reset();
          $scope.dataSuccess = true;
        }, 
         function(err){
              var errors = err.data;              
              console.log(errors);
              angular.forEach(errors, function(error){
                if (error.message=='CNPJ_EXISTS') {
                  $scope.cnpjInvalid=true;
                };
               if (error.message=='CNPJ_INVALID') {
                  $scope.cnpjInvalid=true;
                };
              });
          
        });  
        }
  			
  	};//end of save 
  	

    $scope.edit = function(med){
      $scope.medicalInstitutional = med;
      $scope.editCnpj = false;
       $scope.dataSuccess = false;
    };

   //update
    $scope.update = function(){
      $scope.medicalInstitutional.$update(function(){
        $scope.listMedicalInstitutional = MedicalInstitutionalService.list();
        $scope.reset();
         $scope.dataSuccess = true;

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
        $scope.editCnpj = true;
         $scope.razaoSocialInvalid=false;
  	};
  
  }]);
