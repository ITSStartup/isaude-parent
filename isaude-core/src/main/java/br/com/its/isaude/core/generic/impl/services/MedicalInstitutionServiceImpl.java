package br.com.its.isaude.core.generic.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.its.isaude.core.generic.interfaces.MedicalInstitutionService;
import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
@Service
public class MedicalInstitutionServiceImpl extends GenericServiceImpl<MedicalInstitutional, MedicalInstitutionDAO> implements MedicalInstitutionService {

	@Autowired
	@Qualifier("medicalInstitutionDAOImpl")
	@Override
	public void setDao(MedicalInstitutionDAO dao) {
		super.setDao(dao);
	}

}
