package edu.link.jpa.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {

	_INSTANCE;
	
	private EntityManagerFactory emf;
	
	private PersistenceManager(){
		emf = Persistence.createEntityManagerFactory("HR_PU");
	}
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public void close(){
		emf.close();
	}
}
