package mem_mgmt;

import java.io.Serializable;
import java.util.HashMap;

import dataaccess.DataAccessFacade;
import dataaccess.DataAccess;

public class LibraryMember extends Person implements Serializable{
	private static final long serialVersionUID = 5364845406817457984L;
	private String memberId;
	private DataAccessFacade dataAccessFacade;
	private static HashMap<String, LibraryMember> members = LibraryMember.getDataAccessInstance().readMemberMap();
	private static DataAccess dataAccess;
	
	public static DataAccess getDataAccessInstance() {
		if(dataAccess == null)
			dataAccess = new DataAccessFacade();
		return dataAccess;
	}
	
	public LibraryMember(String memberId, String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public static LibraryMember searchMemberById(String memberId){
		return LibraryMember.getDataAccessInstance().searchMemberById(memberId);
	}
	
	public static HashMap<String, LibraryMember> loadMembersBySearchText(String searchText)
	{
		return LibraryMember.getDataAccessInstance().loadMembersBySearchText(searchText);
	}
	
	public void saveMember() {
		LibraryMember.getDataAccessInstance().saveNewMember(this);
	}
	
	public static void removeMember(String memberId) {
		LibraryMember.getDataAccessInstance().removeMember(memberId);
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
	
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " / " + this.getPhoneNumber();
	}
}
