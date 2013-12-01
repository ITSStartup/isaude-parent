package br.com.its.isaude.core.impl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.impl.dao.GenericHibernateDAO;
import br.com.its.isaude.core.generic.interfaces.dao.DoctorDAO;
import br.com.its.isaude.core.modal.domain.Doctor;

@Repository
public class DoctorDAOImpl extends GenericHibernateDAO<Doctor> implements
		DoctorDAO {

	public DoctorDAOImpl() {
		super(Doctor.class);

	}

	@Override
	public List<Doctor> list() {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("especialidadeMedicas", FetchMode.JOIN);
		criteria.addOrder(Order.desc("id"));
		final List<Doctor> listDoctors = criteria.list();
		return listDoctors;
	}

	@Override
	public Doctor searchDoctorByCRM(String crm) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.ilike("crm", crm));
		return (Doctor) criteria.uniqueResult();
	}

	@Override
	public Doctor searchDoctorByEmail(String email) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.ilike("email", email));
		return (Doctor) criteria.uniqueResult();
	}
	
	
}
