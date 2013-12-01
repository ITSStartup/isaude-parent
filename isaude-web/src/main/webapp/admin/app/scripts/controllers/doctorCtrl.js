'use strict';

angular.module('iSaudeAdminApp')

.controller('DoctorCtrl', ['$scope','MedicalSpecialityService','DoctorService', 

	function ($scope, MedicalSpecialityService, DoctorService) {

		$scope.prepareValidations = function() {

			$scope.dataCannotBelisted = false;

			$scope.dataSuccess=false;
			$scope.dataNotSuccess=false;

			$scope.crmExists=false;
			$scope.doctorEmailExists=false;

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

		}

		$scope.doctors = [];
		$scope.fillSpecialities();
		$scope.reset();
		$scope.list();

	}

]);