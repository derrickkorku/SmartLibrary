package mem_mgmt;

import java.io.Serializable;

import dataaccess.DataAccessFacade;

public class LibraryMember extends Person implements Serializable{
	private static final long serialVersionUID = 5364845406817457984L;
	private String memberId;
	private DataAccessFacade dataAccessFacade;
	
	public LibraryMember(String firstName, String lastName, String phoneNumber, String memberId, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.memberId = memberId;
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
