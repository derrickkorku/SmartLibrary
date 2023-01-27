package checkout;

import java.io.Serializable;
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
