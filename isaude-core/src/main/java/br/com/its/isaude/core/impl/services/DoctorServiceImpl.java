package br.com.its.isaude.core.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.its.isaude.core.exception.DoctorException;
import br.com.its.isaude.core.exception.enums.MessageResponseStatusEnum;
import br.com.its.isaude.core.generic.impl.services.GenericServiceImpl;
import br.com.its.isaude.core.generic.interfaces.dao.DoctorDAO;
import br.com.its.isaude.core.interfaces.services.DoctorService;
import br.com.its.isaude.core.modal.domain.Doctor;

@Service
public class DoctorServiceImpl extends GenericServiceImpl<Doctor, DoctorDAO> implements	DoctorService {

	@Autowired
	@Qualifier("doctorDAOImpl")
	@Override
	public void setDao(DoctorDAO dao) {
		
		super.setDao(dao);
	}
	
	@Override
	public void save(Doctor entity) throws DoctorException {
		checkCrm(entity.getCrm());
		checkEmail(entity.getEmail());
		getDao().save(entity);
	}

	private void checkEmail(String email) throws DoctorException {
		Doctor doctor = getDao().searchDoctorByEmail(email);
		if(doctor!=null){
			throw new DoctorException(MessageResponseStatusEnum.DOCTOR_EMAIL_EXISTS);
		}
	}

	private void checkCrm(String crm) throws DoctorException {
		Doctor doctor = getDao().searchDoctorByCRM(crm);
		if (doctor!=null) {
			throw new DoctorException(MessageResponseStatusEnum.CRM_EXISTS);
		}
		
	}
}
