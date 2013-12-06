'use strict';

angular.module('iSaudeAdminApp')

.controller('DoctorCtrl', ['$scope','MedicalSpecialityService','DoctorService', 'MedicalInstitutionalService', 

	function ($scope, MedicalSpecialityService, DoctorService, MedicalInstitutionalService) {

		
		$scope.prepareValidations = function() {

			$scope.dataCannotBelisted = false;

			$scope.dataSuccess=false;
			$scope.dataNotSuccess=false;

			$scope.crmExists=false;
			$scope.doctorEmailExists=false;
			$scope.dataRemoveSuccess = false;
			$scope.disableCrm=false;

		};

		$scope.reset = function() {

			$scope.doctor = new DoctorService();
		
			$scope.prepareValidations();
		
		};

		$scope.fillSpecialities = function() {
			MedicalSpecialityService.list(function(data) {
				$scope.medicalSpecialities=data;
			});
		};

		$scope.getListMedicalInstitutional = function(){
			MedicalInstitutionalService.list(function(data){
				$scope.listMedicalInstitutional =data;
			});
		};

		$scope.list = function() {
			DoctorService.list(function(data) {
				$scope.doctors=data;
			}, function(err) {
				var errors = err.data;
				angular.forEach(errors, function(error) {
					if (error.message == 'CANNOT_BE_LISTED') {
						$scope.dataCannotBelisted = true;
					}
				});
			});
		};

		$scope.info = function(doctor) {
			$scope.doctorInfo = doctor;
		}

		$scope.save = function() {
			if ($scope.doctor.id > 0) {
				$scope.update();
			} else{
				$scope.doctor.$create(function(success) {
					$scope.reset();
					$scope.list();
					$scope.dataSuccess=true;
				}, function(err) {
					$scope.list();
					var errors = err.data;
					angular.forEach(errors, function(error) {
						if (error.message == 'CRM_EXISTS') {
							$scope.crmExists=true;
						} else if (error.message == 'DOCTOR_EMAIL_EXISTS') {
							$scope.doctorEmailExists=true;
						}
					});
				});
			}//end of if/else

		}

		$scope.remove = function(doctor){
			$scope.doctorToRemove = doctor;
		};

		$scope.doRemove = function(){
			$scope.doctorToRemove.$remove({id:$scope.doctorToRemove.id},function(res){
				$scope.doctors = DoctorService.list();
				$scope.doctor = new DoctorService();
				$scope.dataRemoveSuccess = true;
			})
		};

		$scope.edit = function(doctor){

			$scope.doctor = doctor;
			$scope.disableCrm=true;
			$scope.dataSuccess = false;

			var specialitiesSel = angular.copy(doctor.especialidadeMedicas);
			$scope.doctor.especialidadeMedicas = new Array();
			angular.forEach($scope.medicalSpecialities, function(specialityOrig) {
				angular.forEach(specialitiesSel, function(specialitySel) {
					if (specialitySel.id === specialityOrig.id) {
						$scope.doctor.especialidadeMedicas.push(specialityOrig);
					}
				});
			});

			var institutesSel = angular.copy(doctor.instituicaoMedicas);
			$scope.doctor.instituicaoMedicas = new Array();
			angular.forEach($scope.listMedicalInstitutional, function(instituteOrig) {
				angular.forEach(institutesSel, function(instituteSel) {
					if (instituteSel.id === instituteOrig.id) {
						$scope.doctor.instituicaoMedicas.push(instituteOrig);
					}
				});
			});

		};

		$scope.update = function(){
			$scope.doctor.$update(function(){
			$scope.doctors = DoctorService.list();
			$scope.reset();
			$scope.dataSuccess = true;
			})
		};

		$scope.doctors = [];
		$scope.fillSpecialities();
		$scope.reset();
		$scope.list();
		$scope.getListMedicalInstitutional();
	
	}

]);