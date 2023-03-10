package dataaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import book_mgmt.Author;
import book_mgmt.Book;
import checkout.CheckOutEntry;
import checkout.MemberCheckoutRecord;
import mem_mgmt.Address;
import mem_mgmt.LibraryMember;
import user_mgmt.Auth;
import user_mgmt.User;

public class DefaultDataInitializer {
	public static void initializeDefaultData() {
		initializeLibraryMemberData();
		initializeBookData();
		initializeUserData();
		initializeCheckoutRecord();
	}


	// create library members
	private static void initializeLibraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
		members.add(libraryMember);
		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
		members.add(libraryMember);

		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
		members.add(libraryMember);

		DataAccessFacade.loadMemberMap(members);
	}
	
	
	private static void initializeCheckoutRecord() {
		LibraryMember member = LibraryMember.getByMemberID("1002");
		CheckOutEntry entry = new CheckOutEntry(allBooks.get(0).getBookCopy());
		MemberCheckoutRecord record = new MemberCheckoutRecord(member, entry);
		
		DataAccessFacade.loadMemberCheckoutRecordMap(record);	
	}

	/// create books
	private static void initializeBookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);
	}

	///////////// DATA //////////////
	private static List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")

	private static List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	@SuppressWarnings("serial")
	private static List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
		}
	};

	@SuppressWarnings("serial")
	private static List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
		}
	};

	private static void initializeUserData() {
		DataAccessFacade.loadUserMap(allUsers);
	}

	@SuppressWarnings("serial")
	private static List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("lib", "lib", Auth.LIBRARIAN));
			add(new User("admin", "admin", Auth.ADMIN));
			add(new User("both", "both", Auth.BOTH));
		}
	};
}
