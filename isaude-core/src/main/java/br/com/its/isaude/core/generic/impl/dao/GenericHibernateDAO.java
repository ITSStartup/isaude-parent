package br.com.its.isaude.core.generic.impl.dao;

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