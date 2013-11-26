package br.com.its.isaude.core.impl.dao;

import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.impl.dao.GenericHibernateDAO;
import br.com.its.isaude.core.interfaces.dao.MedicalSpecialityDAO;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;

@Repository
public class MedicalSpecialityDAOImpl extends GenericHibernateDAO<MedicalSpeciality> implements MedicalSpecialityDAO {

	public MedicalSpecialityDAOImpl() {
		super(MedicalSpeciality.class);
	}

}
