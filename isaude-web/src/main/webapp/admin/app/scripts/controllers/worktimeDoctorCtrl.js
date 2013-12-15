'use strict';

angular.module('iSaudeAdminApp')

.controller('WorktimeDoctorCtrl', ['$scope', 'WorktimeDoctorService',
    function($scope, WorktimeDoctorService) {

        $scope.idMedico = 1;
        $scope.idInstituicao = 1;

        $scope.prepareValidations = function() {

            $scope.dataCannotBelisted = false;

            $scope.dataSuccess = false;
            $scope.dataNotSuccess = false;

            $scope.dataRemoveSuccess = false;
            $scope.dataRemoveNotSuccess = false;

            $scope.isWorktimeDoctorExists = false;

        };

        $scope.reset = function() {
            $scope.worktimeToRemove = null;
            $scope.worktime = new WorktimeDoctorService();
            $scope.worktime.medico = new Object();
            $scope.worktime.medico.id = $scope.idMedico;
            $scope.worktime.instituicaoMedica = new Object();
            $scope.worktime.instituicaoMedica.id = $scope.idInstituicao;
            $scope.prepareValidations();
            $scope.disableIntervalo = false;
            $scope.list();
        };


        $scope.worktimes = [];

        $scope.list = function() {
            var params = {
                idMedico:$scope.idMedico,
                idInstituicao:$scope.idInstituicao
            };
            WorktimeDoctorService.list(params,function(data) {
                $scope.worktimes = data;
            }, function(err) {
                var errors = err.data;
                angular.forEach(errors, function(error) {
                    if (error.message === 'CANNOT_BE_LISTED') {
                        $scope.dataCannotBelisted = true;
                    }
                });
            });
        };

        $scope.edit = function(worktime) {

            $scope.worktime = worktime;

            $scope.prepareValidations();
            
            $scope.disableIntervalo = true;

        };

        $scope.remove = function(worktime) {

            $scope.worktimeToRemove = worktime;

            $scope.prepareValidations();

        };

        $scope.doRemove = function() {

            if ($scope.worktimeToRemove) {

                $scope.worktimeToRemove.$remove({id: $scope.worktimeToRemove.id}, function(success) {
                    $scope.list();
                    $scope.dataRemoveSuccess = true;
                }, function(err) {
                    var errors = err.data;
                    angular.forEach(errors, function(error) {
                        if (error.message === 'REMOVE_NOT_SUCCESS') {
                            $scope.dataRemoveNotSuccess = true;
                        }
                    });
                });

                $scope.reset();

            }


        };

        $scope.save = function() {

            $scope.prepareValidations();

            var id = 0;

            if ($scope.worktime.id > id) {

                $scope.worktime.$update(function(success) {
                    $scope.reset();
                    $scope.list();
                    $scope.dataSuccess = true;
                }, function(err) {
                    $scope.list();
                    var errors = err.data;
                    angular.forEach(errors, function(error) {
                        if (error.message === 'SAVE_NOT_SUCCESS') {
                            $scope.dataNotSuccess = true;
                        } else if (error.message === 'WORKTIMEDOCTOR_EXISTS') {
                            $scope.isWorktimeDoctorExists = true;
                        }
                    });
                });

            } else {

                $scope.worktime.$create(function(success) {
                    $scope.reset();
                    $scope.list();
                    $scope.dataSuccess = true;
                }, function(err) {
                    $scope.list();
                    var errors = err.data;
                    angular.forEach(errors, function(error) {
                        if (error.message === 'SAVE_NOT_SUCCESS') {
                            $scope.dataNotSuccess = true;
                        } else if (error.message === 'WORKTIMEDOCTOR_EXISTS') {
                            $scope.isWorktimeDoctorExists = true;
                        }
                    });
                });

            }

        };

        $scope.reset();

    }
]);