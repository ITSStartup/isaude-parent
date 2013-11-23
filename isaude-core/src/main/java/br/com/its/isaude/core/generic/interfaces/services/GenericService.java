package br.com.its.isaude.core.generic.interfaces.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface GenericService<T> {
	
	void saveOrUpdate(T entity) throws Exception;

	void delete(T entity) throws Exception;

	T getById(Long id) throws Exception;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	List<T> list() throws Exception;

}