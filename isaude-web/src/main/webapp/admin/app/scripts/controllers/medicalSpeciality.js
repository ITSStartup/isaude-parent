'use strict';

angular.module('iSaudeAdminApp')

.controller('MedicalSpecialityCtrl', ['$scope', 'MedicalSpecialityService',
	function ($scope, MedicalSpecialityService) {
		
		$scope.prepareValidations = function() {

			$scope.isMedicalSpecialityInsertSuccess=false;
			$scope.isMedicalSpecialityEditSuccess=false;
			$scope.isMedicalSpecialityRemoveSuccess=false;

			$scope.isMedicalSpecialityInsertNotSuccess=false;
			$scope.isMedicalSpecialityEditNotSuccess=false;
			$scope.isMedicalSpecialityRemoveNotSuccess=false;

			$scope.isDescriptionDuplicated=false;

			$scope.specialitiesCannotBelisted = false;
			
		};

		$scope.reset = function() {
		
			$scope.specialityToRemove = null;

			$scope.speciality = new MedicalSpecialityService();
		
			$scope.prepareValidations();
		
		};


		$scope.specialities = [];

		$scope.saveOrUpdateSuccess = function(success) {
			$scope.reset();
			$scope.list();
			if (success.message == 'INSERT_SUCCESS') {
				$scope.isMedicalSpecialityInsertSuccess=true;
			} else if (success.message == 'EDIT_SUCCESS') {
				$scope.isMedicalSpecialityEditSuccess=true;
			}
		};
		
		$scope.saveOrUpdateError = function(err) {
			$scope.list();
			var errors = err.data;
			angular.forEach(errors, function(error) {
				if (error.message == 'INSERT_NOT_SUCCESS') {
					$scope.isMedicalSpecialityInsertNotSuccess=true;
				} else if (error.message == 'EDIT_NOT_SUCCESS') {
					$scope.isMedicalSpecialityEditNotSuccess=true;
				} else if (error.message == 'DESCRIPTION_DUPLICATED') {
					$scope.isDescriptionDuplicated=true;
				}
			});
		};

		$scope.list = function() {
			MedicalSpecialityService.list(function(data) {
				$scope.specialities=data;
			}, function(err) {
				var errors = err.data;
				angular.forEach(errors, function(error) {
					if (error.message == 'CANNOT_BE_LISTED') {
						$scope.specialitiesCannotBelisted = true;
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
					if (success.message == 'REMOVE_SUCCESS') {
						$scope.isMedicalSpecialityRemoveSuccess=true;
					}
				}, function(err) {
					var errors = err.data;
					angular.forEach(errors, function(error) {
						if (error.message == 'REMOVE_NOT_SUCCESS') {
							$scope.isMedicalSpecialityRemoveNotSuccess=true;
						}
					});
				});

				$scope.reset();

			}


		};

		$scope.save = function() {
			
			$scope.prepareValidations();

			if ($scope.speciality.id > 0) {

				$scope.speciality.$update($scope.saveOrUpdateSuccess, $scope.saveOrUpdateError);

			} else {

				$scope.speciality.$create($scope.saveOrUpdateSuccess, $scope.saveOrUpdateError);

			}
			
		};

		$scope.reset();
		$scope.list();

	}
]);