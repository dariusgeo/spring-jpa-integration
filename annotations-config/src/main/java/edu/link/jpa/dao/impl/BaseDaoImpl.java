package edu.link.jpa.dao.impl;

import edu.link.jpa.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void persist(T entity) {		
		em.persist(entity);
	}

	@Override
	public T read(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}

	@Override
	public void remove(T entity) {
		em.remove(entity);
	}

	@Override
	public void refresh(T entity) {
		em.refresh(entity);
	}
	
	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	protected List<T> readAll(String hql, Class<T> clazz) {		
		TypedQuery<T> query = typedQuery(hql, clazz);		
		return query.getResultList();
	}

	protected Query query(String hql) {
		return em.createQuery(hql);

	}

	protected TypedQuery<T> typedQuery(String hql, Class<T> clazz) {
		return em.createQuery(hql, clazz);
	}

	protected List<T> select(CriteriaQuery<T> criteriaQuery) {

		TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
		List<T> result = typedQuery.getResultList();

		return result;
	}
}
