package br.com.its.isaude.core.generic.impl.dao;

import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;

public class MedicalInstitutionDAOImpl extends GenericHibernateDAO<MedicalInstitutional> implements
		MedicalInstitutionDAO {

	public MedicalInstitutionDAOImpl() {
		super(MedicalInstitutional.class);
	}


}
