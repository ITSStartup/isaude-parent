'use strict';

describe('Service: Worktimedoctor', function () {

  // load the service's module
  beforeEach(module('adminApp'));

  // instantiate service
  var Worktimedoctor;
  beforeEach(inject(function (_Worktimedoctor_) {
    Worktimedoctor = _Worktimedoctor_;
  }));

  it('should do something', function () {
    expect(!!Worktimedoctor).toBe(true);
  });

});
