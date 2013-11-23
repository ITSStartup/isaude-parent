package br.com.its.isaude.core.generic.impl.dao;

import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.model.domain.InstituicaoMedica;

public class MedicalInstitutionDAOImpl extends GenericHibernateDAO<InstituicaoMedica> implements
		MedicalInstitutionDAO {

	public MedicalInstitutionDAOImpl() {
		super(InstituicaoMedica.class);
	}


}
