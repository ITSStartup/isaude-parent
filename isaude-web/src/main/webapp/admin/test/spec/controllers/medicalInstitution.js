'use strict';

describe('Controller: MedicalinstitutionCtrl', function () {

  // load the controller's module
  beforeEach(module('adminApp'));

  var MedicalinstitutionCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MedicalinstitutionCtrl = $controller('MedicalinstitutionCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
