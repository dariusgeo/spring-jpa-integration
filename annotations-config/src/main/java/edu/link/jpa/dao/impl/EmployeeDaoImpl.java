package edu.link.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.link.jpa.dao.EmployeeDao;
import edu.link.jpa.model.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public Employee getEmployeeById(Long id) {

		return read(Employee.class, id);
	}

	@Override
	public void save(Employee employee) {

		persist(employee);
	}

	public List<Employee> getAllEmployees() {

		return readAll("SELECT e FROM Employee e", Employee.class);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		TypedQuery<Employee> query = typedQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public List<Employee> getEmployeesHavingSalaryOverAmount(String departmentName, Double salary) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT e FROM Employee e ");
		queryBuilder.append(" JOIN Department d ON d.departmentId = e.department.id ");
		queryBuilder.append(" WHERE d.name = :deptName ");
		queryBuilder.append(" AND e.salary > :salary");
		
		TypedQuery<Employee> query = typedQuery(queryBuilder.toString(), Employee.class);
		query.setParameter("deptName", departmentName);
		query.setParameter("salary", salary);
		
		return query.getResultList();  
	}

	@Override
	public void updateEmployee(Employee employee) {
		super.update(employee);
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String departmentName) {
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findByDepartment", Employee.class);
		query.setParameter("deptName", departmentName);
		
		return query.getResultList();
	}
}
