package dataaccess;

import java.util.HashMap;

import book_mgmt.Book;
import mem_mgmt.LibraryMember;
import user_mgmt.User;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public LibraryMember searchMemberById(String memberId);
	public HashMap<String, LibraryMember> loadMembersBySearchText(String searchText);	
	public void saveNewMember(LibraryMember member);
	public void removeMember(String memberId); 
}
