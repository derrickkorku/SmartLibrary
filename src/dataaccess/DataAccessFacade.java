package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import book_mgmt.Book;
import checkout.MemberCheckoutRecord;
import mem_mgmt.LibraryMember;
import user_mgmt.User;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUTRECORDS;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);	
	}
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}
	
	
	public void saveNewCheckoutRecord(MemberCheckoutRecord record) {
		HashMap<String, MemberCheckoutRecord> records = readCheckoutRecordMap();
		
		String memberId = record.getMember().getMemberId();
		records.put(memberId, record);
		saveToStorage(StorageType.CHECKOUTRECORDS, records);	
	}
		
		
	@SuppressWarnings("unchecked")
	public HashMap<String, MemberCheckoutRecord> readCheckoutRecordMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> CheckOutEntry
		return (HashMap<String, MemberCheckoutRecord>) readFromStorage(StorageType.CHECKOUTRECORDS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void loadMemberCheckoutRecordMap(List<MemberCheckoutRecord> recordList) {
		HashMap<String, MemberCheckoutRecord> records = new HashMap<String, MemberCheckoutRecord>();
		recordList.forEach(r -> records.put(r.getMember().getMemberId(), r));
		saveToStorage(StorageType.CHECKOUTRECORDS, records);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}

	@Override
	public LibraryMember searchMemberById(String memberId) {
		HashMap<String, LibraryMember>  mems = readMemberMap();
		 if(mems.containsKey(memberId)) {
			 return mems.get(memberId);
		 }
		 return null;
	}

	@Override
	public HashMap<String, LibraryMember> loadMembersBySearchText(String searchText) {
		HashMap<String, LibraryMember> recordsMap = readMemberMap();
		HashMap<String, LibraryMember> tempMembers = new HashMap<String, LibraryMember>();
		for (String key : recordsMap.keySet()) 
		{
			LibraryMember m = recordsMap.get(key);
			if(
					m.getMemberId().toLowerCase().contains(searchText.toLowerCase()) ||
					m.getFirstName().toLowerCase().contains(searchText.toLowerCase()) ||
					m.getLastName().toLowerCase().contains(searchText.toLowerCase()) 
				) 
			{
				tempMembers.put(m.getMemberId(), m);
			}
		}
		return tempMembers;
	}

	@Override
	public void removeMember(String memberId) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		if(mems == null) return;
		mems.remove(memberId);
		saveToStorage(StorageType.MEMBERS, mems);
	}
}
