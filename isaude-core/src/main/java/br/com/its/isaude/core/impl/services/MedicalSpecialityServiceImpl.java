package br.com.its.isaude.core.impl.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.its.isaude.core.exception.MedicalSpecialityException;
import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.generic.impl.services.GenericServiceImpl;
import br.com.its.isaude.core.interfaces.dao.MedicalSpecialityDAO;
import br.com.its.isaude.core.interfaces.services.MedicalSpecialityService;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;

@Service
public class MedicalSpecialityServiceImpl extends GenericServiceImpl<MedicalSpeciality, MedicalSpecialityDAO> implements MedicalSpecialityService {

	@Override
	@Autowired
	@Qualifier("medicalSpecialityDAOImpl")
	public void setDao(MedicalSpecialityDAO dao) {
		super.setDao(dao);
	}
	
	@Override
	public void save(MedicalSpeciality entity) throws Exception {
		try {
			super.save(entity);
		} catch (ConstraintViolationException e) {
			throwConstraintMedicalSpecialityException(e);
		} catch (Exception e) {
			throw new MedicalSpecialityException(MessageResponseStatusEnum.SAVE_NOT_SUCCESS);
		}
	}

	@Override
	public void update(MedicalSpeciality entity) throws Exception {
		try {
			super.update(entity);
		} catch (ConstraintViolationException e) {
			throwConstraintMedicalSpecialityException(e);
		} catch (Exception e) {
			throw new MedicalSpecialityException(MessageResponseStatusEnum.SAVE_NOT_SUCCESS);
		}
	}
	
	@Override
	public void delete(MedicalSpeciality entity) throws Exception {
		try {
			super.delete(entity);
		} catch (Exception e) {
			throw new MedicalSpecialityException(MessageResponseStatusEnum.REMOVE_NOT_SUCCESS);
		}
	}

	private void throwConstraintMedicalSpecialityException(ConstraintViolationException e) throws MedicalSpecialityException {
		if (e.getMessage().contains("Duplicate entry")) {
			throw new MedicalSpecialityException(MessageResponseStatusEnum.DESCRIPTION_DUPLICATED);
		} else throw e;
	}
	
}
