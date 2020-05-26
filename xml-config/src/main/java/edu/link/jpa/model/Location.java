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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="location_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="loc_seq_gen")
	@SequenceGenerator(name="loc_seq_gen", sequenceName="locations_seq")
	private Long locationId;
	
	@Column(name = "street_address")
	private String street;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state_province")
	private String state;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
	private Country country;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
