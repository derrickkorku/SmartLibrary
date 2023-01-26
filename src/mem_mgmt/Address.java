package mem_mgmt;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = -3543001670367107453L;
	private String street;
	private String state;
	private String city;
	private String zip;
	
	public Address(String street, String state, String city, String zip){
		this.city = city;
		this.state = state;
		this.street = street;
		this.setZip(zip);
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
