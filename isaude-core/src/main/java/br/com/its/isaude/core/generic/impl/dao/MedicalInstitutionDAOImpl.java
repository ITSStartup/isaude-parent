package br.com.its.isaude.core.generic.impl.dao;

import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
@Repository
public class MedicalInstitutionDAOImpl extends GenericHibernateDAO<MedicalInstitutional> implements
		MedicalInstitutionDAO {

	public MedicalInstitutionDAOImpl() {
		super(MedicalInstitutional.class);
	}


}
