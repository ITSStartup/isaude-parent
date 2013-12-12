package br.com.its.isaude.core.interfaces.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.its.isaude.core.generic.interfaces.services.GenericService;
import br.com.its.isaude.core.modal.domain.Doctor;

public interface DoctorService extends GenericService<Doctor> {
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	List<Doctor> search(String description);

}
