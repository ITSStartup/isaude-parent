package br.com.its.isaude.core.generic.impl.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.its.isaude.core.generic.interfaces.dao.GenericDAO;
import br.com.its.isaude.core.generic.interfaces.services.GenericService;
@Service
public class GenericServiceImpl<T, DAO extends GenericDAO<T>> implements GenericService<T> {
	
	private DAO dao;
	
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	public DAO getDao() {
		return dao;
	}

	public void saveOrUpdate(T entity) throws Exception {
		dao.saveOrUpdate(entity);
		
	}

	public void delete(T entity) throws Exception {
		dao.delete(entity);
		
	}

	public T getById(Long id) throws Exception {
		
		return dao.getById(id);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<T> list() throws Exception {
		
		return dao.list();
	}


}
