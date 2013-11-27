package br.com.its.isaude.core.generic.interfaces.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface GenericDAO<T> {
	void save(T entity);

	void update(T entity);
	
	void delete(T entity);

	T getById(Long id);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	List<T> list();

	long getCount(T entity);
}