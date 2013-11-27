'use strict';

describe('Service: Medicalinstitutionalservice', function () {

  // load the service's module
  beforeEach(module('AdminApp'));

  // instantiate service
  var Medicalinstitutionalservice;
  beforeEach(inject(function (_Medicalinstitutionalservice_) {
    Medicalinstitutionalservice = _Medicalinstitutionalservice_;
  }));

  it('should do something', function () {
    expect(!!Medicalinstitutionalservice).toBe(true);
  });

});
