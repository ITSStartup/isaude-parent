package br.com.its.isaude.core.generic.impl.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.interfaces.dao.MedicalInstitutionDAO;
import br.com.its.isaude.core.modal.domain.MedicalInstitutional;
@Repository
public class MedicalInstitutionDAOImpl extends GenericHibernateDAO<MedicalInstitutional> implements
		MedicalInstitutionDAO {

	public MedicalInstitutionDAOImpl() {
		super(MedicalInstitutional.class);
	}

	public MedicalInstitutional getByCnpj(String cnpj) {
			Criteria criteria = getCurrentSession().createCriteria(MedicalInstitutional.class);
			criteria.add(Restrictions.eq("cnpj",cnpj));
		return (MedicalInstitutional) criteria.uniqueResult();
	}

	public MedicalInstitutional getByRazaoSocial(String razaoSocial) {
		Criteria criteria = getCurrentSession().createCriteria(MedicalInstitutional.class);
		criteria.add(Restrictions.ilike("razaoSocial", razaoSocial));
		return (MedicalInstitutional) criteria.uniqueResult();
		
	}


}
