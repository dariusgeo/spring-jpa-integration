package edu.link.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="employees")
@NamedQueries({
    @NamedQuery(name="Employee.findByDepartment",
                query="SELECT e FROM Employee e JOIN Department d ON d.departmentId = e.department.id WHERE d.name = :deptName ")
}) 
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="emp_seq_gen")
	@SequenceGenerator(name="emp_seq_gen", sequenceName="employees_seq")
	private Long employeeId;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	@Column(name="email")
	private String email;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="hire_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date hireDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_id")
	private Job job;
	
	@Column(name="salary")
	private Double salary;
	
	@Column(name="commission_pct")
	private Double commission;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
		
	@Version
	@Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
	private int version;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
