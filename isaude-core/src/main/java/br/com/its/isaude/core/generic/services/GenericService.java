package br.com.its.isaude.core.generic.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface GenericService<T> {
	
	void saveOrUpdate(T entity) throws Exception;

	void delete(T entity) throws Exception;

	T getById(Long id) throws Exception;

	List<T> list() throws Exception;

}