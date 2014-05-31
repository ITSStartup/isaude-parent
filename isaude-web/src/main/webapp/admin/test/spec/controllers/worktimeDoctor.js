'use strict';

describe('Controller: WorktimedoctorCtrl', function () {

  // load the controller's module
  beforeEach(module('adminApp'));

  var WorktimedoctorCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WorktimedoctorCtrl = $controller('WorktimedoctorCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
