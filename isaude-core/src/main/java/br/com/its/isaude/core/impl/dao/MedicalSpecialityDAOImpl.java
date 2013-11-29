package br.com.its.isaude.core.impl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.impl.dao.GenericHibernateDAO;
import br.com.its.isaude.core.interfaces.dao.MedicalSpecialityDAO;
import br.com.its.isaude.core.modal.domain.MedicalSpeciality;

@Repository
@SuppressWarnings("unchecked")
public class MedicalSpecialityDAOImpl extends GenericHibernateDAO<MedicalSpeciality> implements MedicalSpecialityDAO {

	public MedicalSpecialityDAOImpl() {
		super(MedicalSpeciality.class);
	}
	
	@Override
	public List<MedicalSpeciality> list() {
		List<MedicalSpeciality> list = getCurrentSession()
			.createCriteria(MedicalSpeciality.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.addOrder(Order.desc("id"))
			.list();
		return list;
	}

}
