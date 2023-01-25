package book_mgmt;

import mem_mgmt.Address;
import mem_mgmt.Person;

public class Author extends Person{

	Author(String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAddress(Address address) {
		super.setAddress(address);
	}

}
