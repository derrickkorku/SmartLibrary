package mem_mgmt;

import java.io.Serializable;
import java.util.HashMap;
import dataaccess.DataAccessFacade;

public class LibraryMember extends Person implements Serializable{
	private static final long serialVersionUID = 5364845406817457984L;
	private String memberId;
	private static HashMap<String, LibraryMember> members = DataAccessFacade.getInstance().readMemberMap();

	
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
		return DataAccessFacade.getInstance().searchMemberById(memberId);
	}
	
	public static HashMap<String, LibraryMember> loadMembersBySearchText(String searchText)
	{
		return DataAccessFacade.getInstance().loadMembersBySearchText(searchText);
	}
	
	public void saveMember() {
		DataAccessFacade.getInstance().saveNewMember(this);
	}
	
	public static void removeMember(String memberId) {
		DataAccessFacade.getInstance().removeMember(memberId);
	}
	
	public static void setMembers(HashMap<String, LibraryMember> members) {
		LibraryMember.members = members;
	}
	
	public static HashMap<String, LibraryMember> getMembers() {
		return members;
	}
	
	public String getName() {
		return this.getFirstName() + " " + this.getLastName();
	}
	
	public static LibraryMember getByMemberID(String memberID) {
		for (String key : members.keySet()) {
			if (key.equals(memberID)) {
				return members.get(key);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " / " + this.getPhoneNumber();
	}
}
