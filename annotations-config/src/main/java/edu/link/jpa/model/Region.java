package edu.link.jpa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "region_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reg_seq_gen")
	@SequenceGenerator(name="reg_seq_gen", sequenceName="regions_seq")
	private Long regionId;

	@Column(name = "region_name")
	private String name;

	@OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
	List<Country> countries;


	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}