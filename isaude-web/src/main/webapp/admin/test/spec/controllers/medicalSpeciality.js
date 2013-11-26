'use strict';

describe('Controller: MedicalspecialityCtrl', function () {

  // load the controller's module
  beforeEach(module('iSaudeAdminApp'));

  var MedicalspecialityCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MedicalspecialityCtrl = $controller('MedicalspecialityCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
