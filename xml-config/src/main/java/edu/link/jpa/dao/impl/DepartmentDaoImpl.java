package edu.link.jpa.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.link.jpa.dao.DepartmentDao;
import edu.link.jpa.model.DepartmentDetails;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<DepartmentDetails> implements DepartmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getDepartmentDetails(String employeeFirstName, String employeeLastname) {
		Query query = em.createNativeQuery("SELECT d.department_name, l.city, l.state_province, c.country_name "
										 + " FROM employees e "
										 + " JOIN departments d ON d.department_id = e.department_id "
										 + " JOIN locations l ON l.location_id = d.location_id "
										 + " JOIN countries c ON c.country_id = l.country_id "
									     + " WHERE "
										 + " e.first_name = ?1 "
										 + " AND "
										 + " e.last_name = ?2 ");
		
		query.setParameter(1, employeeFirstName);
		query.setParameter(2, employeeLastname);
		
		return query.getResultList();
	}

}
