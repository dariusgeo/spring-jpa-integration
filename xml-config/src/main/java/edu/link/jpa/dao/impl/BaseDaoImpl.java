package edu.link.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import edu.link.jpa.dao.BaseDao;
import edu.link.jpa.dao.utils.PersistenceManager;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	//protected EntityManager em = PersistenceManager._INSTANCE.getEntityManager();
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void persist(T entity) {		
		EntityTransaction tx = null;		
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}

			throw new RuntimeException("Exception while trying to persist entity");
		}

	}

	@Override
	public T read(Class<T> clazz, Object id) {

		return em.find(clazz, id);
	}

	@Override
	public void remove(T entity) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(entity);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}

			throw new RuntimeException("Exception while trying to remove entity");
		}

	}

	@Override
	public void refresh(T entity) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.refresh(entity);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}

			throw new RuntimeException("Exception while trying to refresh entity");
		}

	}
	
	@Override
	public void update(T entity) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback();
			}

			throw new RuntimeException("Exception while trying to merge entity");
		}

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
