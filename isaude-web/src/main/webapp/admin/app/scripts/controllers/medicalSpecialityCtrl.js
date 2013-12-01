'use strict';

angular.module('iSaudeAdminApp')

.controller('MedicalSpecialityCtrl', ['$scope', 'MedicalSpecialityService',
	function ($scope, MedicalSpecialityService) {
		
		$scope.prepareValidations = function() {

			$scope.dataCannotBelisted = false;

			$scope.dataSuccess=false;
			$scope.dataNotSuccess=false;

			$scope.dataRemoveSuccess=false;
			$scope.dataRemoveNotSuccess=false;

			$scope.isDescriptionDuplicated=false;
			
		};

		$scope.reset = function() {

			$scope.specialityToRemove = null;

			$scope.speciality = new MedicalSpecialityService();
		
			$scope.prepareValidations();
		
		};


		$scope.specialities = [];

		$scope.list = function() {
			MedicalSpecialityService.list(function(data) {
				$scope.specialities=data;
			}, function(err) {
				var errors = err.data;
				angular.forEach(errors, function(error) {
					if (error.message == 'CANNOT_BE_LISTED') {
						$scope.dataCannotBelisted = true;
					}
				});
			});
		};

		$scope.edit = function(speciality) {

			$scope.speciality = speciality;
			
			$scope.prepareValidations();
		
		};

		$scope.remove = function(speciality) {

			$scope.specialityToRemove = speciality;

			$scope.prepareValidations();
			
		};

		$scope.doRemove = function() {

			if ($scope.specialityToRemove) {

				$scope.specialityToRemove.$remove({id : $scope.specialityToRemove.id}, function(success) {
					$scope.list();
					$scope.dataRemoveSuccess=true;
				}, function(err) {
					var errors = err.data;
					angular.forEach(errors, function(error) {
						if (error.message == 'REMOVE_NOT_SUCCESS') {
							$scope.dataRemoveNotSuccess=true;
						}
					});
				});

				$scope.reset();

			}


		};

		$scope.save = function() {
			
			$scope.prepareValidations();
			
			var id = 0;

			if ($scope.speciality.id > id) {

				$scope.speciality.$update(function(success) {
					$scope.reset();
					$scope.list();
					$scope.dataSuccess=true;
				}, function(err) {
					$scope.list();
					var errors = err.data;
					angular.forEach(errors, function(error) {
						if (error.message == 'SAVE_NOT_SUCCESS') {
							$scope.dataNotSuccess=true;
						} else if (error.message == 'DESCRIPTION_DUPLICATED') {
							$scope.isDescriptionDuplicated=true;
						}
					});
				});

			} else {

				$scope.speciality.$create(function(success) {
					$scope.reset();
					$scope.list();
					$scope.dataSuccess=true;
				}, function(err) {
					$scope.list();
					var errors = err.data;
					angular.forEach(errors, function(error) {
						if (error.message == 'SAVE_NOT_SUCCESS') {
							$scope.dataNotSuccess=true;
						} else if (error.message == 'DESCRIPTION_DUPLICATED') {
							$scope.isDescriptionDuplicated=true;
						}
					});
				});

			}
			
		};

		$scope.reset();
		$scope.list();

	}
]);