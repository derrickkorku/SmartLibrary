package mem_mgmt;

import java.util.HashMap;

public class LibraryMemberController {
	public static HashMap<String, LibraryMember> loadMembers(){
		return LibraryMember.getMembers();
	}
	
	public static LibraryMember getByMemberID(String memberID) {
		return LibraryMember.getByMemberID(memberID);
	}
	
	public static HashMap<String, LibraryMember> loadMembersBySearchText(String searchText){
		return LibraryMember.loadMembersBySearchText(searchText);
	}
	
	public static LibraryMember searchMemberById(String memberId) 
	{
		return LibraryMember.searchMemberById(memberId);
	}
	
	public static void saveNewMember(LibraryMember member) 
	{
		member.saveMember();
	}
	
	public static void removeMember(String memberId) 
	{
		LibraryMember.removeMember(memberId);
	}
}
