package br.com.its.isaude.core.impl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.impl.dao.GenericHibernateDAO;
import br.com.its.isaude.core.generic.interfaces.dao.DoctorDAO;
import br.com.its.isaude.core.modal.domain.Doctor;

@Repository
public class DoctorDAOImpl extends GenericHibernateDAO<Doctor> implements DoctorDAO {

    public DoctorDAOImpl() {
        super(Doctor.class);
    }

    @Override
    public List<Doctor> list() {
        Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFetchMode("especialidadeMedicas", FetchMode.JOIN);
        criteria.setFetchMode("instituicaoMedicas", FetchMode.JOIN);
        criteria.setFetchMode("expedienteMedicos", FetchMode.JOIN);
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

	@Override
	public List<Doctor> search(String description) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass(),"d");
		criteria.setFetchMode("d.especialidadeMedicas", FetchMode.JOIN);
		criteria.createAlias("d.especialidadeMedicas", "e");
		Criterion crm = Restrictions.ilike("crm", description,MatchMode.ANYWHERE);
		Criterion name = Restrictions.ilike("nome",description,MatchMode.ANYWHERE);
		Criterion lastname = Restrictions.ilike("sobrenome",description,MatchMode.ANYWHERE);
		Criterion specialityDoctor = Restrictions.ilike("e.description",description,MatchMode.ANYWHERE);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(crm);
		disjunction.add(name);
		disjunction.add(lastname);
		disjunction.add(specialityDoctor);
		criteria.add(disjunction);
		List<Doctor> listDoctors = criteria.list();
		return listDoctors;
	}

}
