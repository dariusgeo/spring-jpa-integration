package edu.link.jpa.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerUtils {
	
	private static EntityManagerFactory emFactory;
	private static EntityManager em;
	
	private EntityManagerUtils(){
		
	}
	
	public static synchronized EntityManager em(){
		
		if (em == null){
			emFactory = Persistence.createEntityManagerFactory("HR_PU");
			em = emFactory.createEntityManager();
		}
		
		return em;
	}

}
