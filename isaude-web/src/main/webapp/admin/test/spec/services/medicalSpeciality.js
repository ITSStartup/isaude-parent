'use strict';

describe('Service: medicalSpeciality', function () {

  // load the service's module
  beforeEach(module('iSaudeAdminApp'));

  // instantiate service
  var medicalSpeciality;
  beforeEach(inject(function(_medicalSpeciality_) {
    medicalSpeciality = _medicalSpeciality_;
  }));

  it('should do something', function () {
    expect(!!medicalSpeciality).toBe(true);
  });

});
