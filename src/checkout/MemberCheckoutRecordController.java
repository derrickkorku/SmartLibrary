package checkout;

import book_mgmt.BookCopy;
import mem_mgmt.LibraryMember;

public class MemberCheckoutRecordController {
	
	public static MemberCheckoutRecord createMemberCheckoutRecord(BookCopy bookCopy, LibraryMember member) {
		CheckOutEntry checkOutEntry = createCheckOutEntry(bookCopy);
		return MemberCheckoutRecord.createRecord(member, checkOutEntry);
	}
	
	private static CheckOutEntry createCheckOutEntry(BookCopy bookCopy) {
		return new CheckOutEntry(bookCopy);
	}
//	public static getData(LibraryMember member) {
//		
//	}
}
