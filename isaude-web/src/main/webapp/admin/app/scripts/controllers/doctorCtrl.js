'use strict';

angular.module('iSaudeAdminApp')

.controller('DoctorCtrl', ['$scope','MedicalSpecialityService','DoctorService', 

	function ($scope, MedicalSpecialityService, DoctorService) {

		$scope.phonePattern = /^\(?(\d{2})\)?[ .-]?(\d{4})[ .-]?(\d{4})$/;

		$scope.prepareValidations = function() {

			$scope.dataCannotBelisted = false;

			$scope.dataSuccess=false;
			$scope.dataNotSuccess=false;

			$scope.crmExists=false;
			$scope.doctorEmailExists=false;
			$scope.dataRemoveSuccess = false;

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
		}

		$scope.doRemove = function(){
			$scope.doctorToRemove.$remove({id:$scope.doctorToRemove.id},function(res){
				$scope.list = DoctorService.list();
				$scope.doctor = new DoctorService();
				$scope.dataRemoveSuccess = true;
			})
		}

		$scope.edit = function(doc){
			$scope.doctor = doc;
			$scope.showCrm=false;
			$scope.dataSuccess = false;
		}
		$scope.update = function(){
			$scope.doctor.$update(function(){
			$scope.list = DoctorService.list();
			$scope.reset();
			$scope.dataSuccess = true;
			})
		}

		$scope.doctors = [];
		$scope.fillSpecialities();
		$scope.reset();
		$scope.list();

	}

]);