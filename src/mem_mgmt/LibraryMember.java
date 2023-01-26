package mem_mgmt;

import java.io.Serializable;
import java.util.HashMap;

import dataaccess.DataAccessFacade;

public class LibraryMember extends Person implements Serializable{
	private static final long serialVersionUID = 5364845406817457984L;
	private String memberId;
	private DataAccessFacade dataAccessFacade;
	private static HashMap<String, LibraryMember> members = new DataAccessFacade().readMemberMap();
	
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
	
	public static void setMembers(HashMap<String, LibraryMember> members) {
		LibraryMember.members = members;
	}
	
	public static HashMap<String, LibraryMember> getMembers() {
		return members;
	}
	
	public static LibraryMember getByMemberID(String memberID) {
		for (String key : members.keySet()) {
			if (key.equals(memberID)) {
				return members.get(key);
			}
		}
		
		return null;
	}

	public DataAccessFacade getDataAccessFacade() {
		return dataAccessFacade;
	}

	public void setDataAccessFacade(DataAccessFacade dataAccessFacade) {
		this.dataAccessFacade = dataAccessFacade;
	}
}
