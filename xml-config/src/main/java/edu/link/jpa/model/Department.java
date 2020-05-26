package edu.link.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="departments")
public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dept_seq_gen")
	@SequenceGenerator(name="dept_seq_gen", sequenceName="departments_seq")
	private Long departmentId;
	
	@Column(name="department_name")
	private String name;
	
	@OneToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id")
	private Location location;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}