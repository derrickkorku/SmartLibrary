package book_mgmt;

import java.io.Serializable;

import mem_mgmt.Address;
import mem_mgmt.Person;

public class Author extends Person implements Serializable{
	private static final long serialVersionUID = -2955676790698657303L;
	private String shortBio;
	
	public Author(String firstName, String lastName, String phoneNumber, Address address, String shortBio) {
		super(firstName, lastName, phoneNumber, address);
		this.shortBio = shortBio;
	}

	public String getShortBio() {
		return shortBio;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
}
