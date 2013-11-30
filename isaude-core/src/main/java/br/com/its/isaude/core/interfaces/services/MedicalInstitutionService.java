package br.com.its.isaude.core.interfaces.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.its.isaude.core.generic.interfaces.services.GenericService;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;

public interface MedicalInstitutionService extends GenericService<MedicalInstitutional>{
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	MedicalInstitutional getByCnpj(String cnpj);

}
