package edu.link.jpa.dao;


public interface BaseDao<T> {

	void persist(T entity);
	
	T read(Class<T> clazz, Object id);
	
	void remove(T entity);
	
	void refresh(T entity);
	
	void update(T entity);
}
