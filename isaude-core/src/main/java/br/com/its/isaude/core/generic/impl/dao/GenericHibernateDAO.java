package br.com.its.isaude.core.generic.impl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.its.isaude.core.generic.interfaces.dao.GenericDAO;

@Repository
@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {

	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;
	
	//override code here
	public void save(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}


	public T getById(Long id) {
		return (T) getCurrentSession().get(persistentClass, id);
	}
	
	public List<T> list() {
		List<T> list = getCurrentSession()
			.createCriteria(persistentClass)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
		return list;
	}
	
	
	public long getCount(T entity) {
		return getCurrentSession().createCriteria(persistentClass).list().size();
	}
	
	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public GenericHibernateDAO(Class<T> persistenClass) {
		super();
		this.persistentClass = persistenClass;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	};

}