package edu.link.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="job_history")
public class JobHistory implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId JobHistoryPK jobHistoryID;
	
	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="job_id")
	private Job job;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
	private Department department;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public JobHistoryPK getJobHistoryID() {
		return jobHistoryID;
	}

	public void setJobHistoryID(JobHistoryPK jobHistoryID) {
		this.jobHistoryID = jobHistoryID;
	}

//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}
}