'use strict';

describe('Service: Doctorservice', function () {

  // load the service's module
  beforeEach(module('AdminApp'));

  // instantiate service
  var Doctorservice;
  beforeEach(inject(function (_Doctorservice_) {
    Doctorservice = _Doctorservice_;
  }));

  it('should do something', function () {
    expect(!!Doctorservice).toBe(true);
  });

});
