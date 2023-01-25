package mem_mgmt;

import dataaccess.DataAccessFacade;

public class LibraryMember extends Person{
	private String memberId;
	private DataAccessFacade dataAccessFacade;
	
	public LibraryMember(String firstName, String lastName, String phoneNumber, String memberId) {
		super(firstName, lastName, phoneNumber);
		this.memberId = memberId;
	}
	
	@Override
	public void addAddress(Address address) {
		super.setAddress(address);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public void saveMember() {
		this.getDataAccessFacade().saveNewMember(this);
	}

	public DataAccessFacade getDataAccessFacade() {
		return dataAccessFacade;
	}

	public void setDataAccessFacade(DataAccessFacade dataAccessFacade) {
		this.dataAccessFacade = dataAccessFacade;
	}
}
