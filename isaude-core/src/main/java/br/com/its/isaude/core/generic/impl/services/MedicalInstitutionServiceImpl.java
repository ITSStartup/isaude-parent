package br.com.its.isaude.core.generic.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.its.isaude.core.enums.MsgResponseEnum;
import br.com.its.isaude.core.exception.MedicalInstitutionException;
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

	public MedicalInstitutional getByCnpj(String cnpj) {
		final MedicalInstitutional medicalInstitutional = getDao().getByCnpj(cnpj);
		return medicalInstitutional;
	}
	
	@Override
	public void save(MedicalInstitutional entityMedicalInstitutional) throws MedicalInstitutionException {
//		TODO refactoring Design Pattern 
		validateCnpjExist(entityMedicalInstitutional);
		validateRazaoSocial(entityMedicalInstitutional);
		getDao().save(entityMedicalInstitutional);
	}

	private void validateRazaoSocial(	MedicalInstitutional entityMedicalInstitutional)	throws MedicalInstitutionException {
		MedicalInstitutional medicalInstitutionalByRazaoSocial = getDao().getByRazaoSocial(entityMedicalInstitutional.getRazaoSocial());
		if (medicalInstitutionalByRazaoSocial!=null) {
			throw new MedicalInstitutionException(MsgResponseEnum.RAZAO_SOCIAL_EXISTS);
		}
	}

	private void validateCnpjExist(MedicalInstitutional entityMedicalInstitutional)	throws MedicalInstitutionException {
		MedicalInstitutional medicalInstitutional = getDao().getByCnpj(entityMedicalInstitutional.getCnpj());
		if(medicalInstitutional!=null){
			throw new MedicalInstitutionException(MsgResponseEnum.CNPJ_EXISTS);
		}
	}

}
