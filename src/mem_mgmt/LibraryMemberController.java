package mem_mgmt;

import java.util.HashMap;

public class LibraryMemberController {
	public static HashMap<String, LibraryMember> loadMembers(){
		return LibraryMember.getMembers();
	}
	
	public static LibraryMember getByMemberID(String memberID) {
		return LibraryMember.getByMemberID(memberID);
	}
}
