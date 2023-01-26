package checkout;

import book_mgmt.BookCopy;

public class CheckOutEntryController {
	public static CheckOutEntry createEntry(BookCopy bookCopy) {
		return new CheckOutEntry(bookCopy);
	}
}
