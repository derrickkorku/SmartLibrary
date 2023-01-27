package checkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccessFacade;
import mem_mgmt.LibraryMember;

public class MemberCheckoutRecord implements Serializable{
	private static final long serialVersionUID = -3478361232389930596L;
	private LibraryMember member;
	private CheckOutEntry checkoutEntry;
	
	public MemberCheckoutRecord(LibraryMember member, CheckOutEntry checkoutEntry){
		this.member = member;
		this.checkoutEntry = checkoutEntry;
	}
	
	public CheckOutEntry getCheckoutEntry() {
		return checkoutEntry;
	}
	public void setCheckoutEntry(CheckOutEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}
	public LibraryMember getMember() {
		return member;
	}
	public void setMember(LibraryMember member) {
		this.member = member;
	}
	
	public static List<MemberCheckoutRecord> getMemberRecords(LibraryMember member) {
		HashMap<String, List<MemberCheckoutRecord>> records = DataAccessFacade.getInstance().readCheckoutRecordMap();
		
		
		
		for (String key : records.keySet()) {
			if (key.equals(member.getMemberId())) {
				return records.get(key);
			}
		}
		
		return null;
		
	}
	
	
	public static List<MemberCheckoutRecord> getMemberRecordsByMemberID(String memberID){
		HashMap<String, List<MemberCheckoutRecord>> records = DataAccessFacade.getInstance().readCheckoutRecordMap();
		
		for (String key : records.keySet()) {
			if (key.equals(memberID)) {
				return records.get(key);
			}
		}
		
		return null;
		
	}
	
	public static List<MemberCheckoutRecord> getMemberRecordsByISBN(String isbn){
		HashMap<String, List<MemberCheckoutRecord>> records = DataAccessFacade.getInstance().readCheckoutRecordMap();
		
		List<MemberCheckoutRecord> list = new ArrayList<MemberCheckoutRecord>();
		
		for (List<MemberCheckoutRecord> record : records.values()) {
			for (int i = 0; i < record.size(); i++) {
				if (record.get(i).getCheckoutEntry().getBookCopy().getBook().getIsbn().contains(isbn)) {
					list.add(record.get(i));
				}
			}
		}
	
		return list;
		
	}
	
	
	public static MemberCheckoutRecord createRecord(LibraryMember member, CheckOutEntry entry) {
		MemberCheckoutRecord record = new MemberCheckoutRecord(member, entry);
		try {
			DataAccessFacade.getInstance().saveNewCheckoutRecord(record);
			 return record;
		}catch(Exception ex) {
			System.out.println("Eerror" + ex);
			return null;
		}
	}
}
