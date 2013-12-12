package br.com.its.isaude.core.interfaces.services;

import java.util.List;

import br.com.its.isaude.core.generic.interfaces.services.GenericService;
import br.com.its.isaude.core.modal.domain.Doctor;

public interface DoctorService extends GenericService<Doctor> {

	List<Doctor> search(String description);

}
