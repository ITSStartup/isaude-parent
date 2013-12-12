	package br.com.its.isaude.core.generic.interfaces.dao;

import java.util.List;

import br.com.its.isaude.core.modal.domain.Doctor;

public interface DoctorDAO extends GenericDAO<Doctor> {

	Doctor searchDoctorByCRM(String CRM);

	Doctor searchDoctorByEmail(String email);

	List<Doctor> search(String description);

}
