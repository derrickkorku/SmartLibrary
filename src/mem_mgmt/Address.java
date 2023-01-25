package mem_mgmt;

public class Address {
	private String street;
	private String state;
	private String city;
	private int zip;
	
	public Address(String street, String state, String city, int zip){
		this.city = city;
		this.state = state;
		this.street = street;
		this.zip = zip;
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
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
}
