package book_mgmt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccessFacade;

public class Book implements Serializable {
	private static final long serialVersionUID = -8563941648780540988L;
	private String isbn;
	private String title;
	private int maxBorrowedDays;
	private List<Author> authors;
	private List<BookCopy> bookCopies = new ArrayList<BookCopy>();
	private static HashMap<String, Book> books = DataAccessFacade.getInstance().readBooksMap();
	
	public Book(String isbn, String title, int maxBorrowedDays, List<Author> list) {
		this.title = title;
		this.isbn = isbn;
		this.setMaxBorrowedDays(maxBorrowedDays);
		this.setAuthors(list);
	}

	public Book addCopy() {
		BookCopy copy = new BookCopy(this.bookCopies.size() + 1, this);
		this.bookCopies.add(copy);
		return this;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMaxBorrowedDays() {
		return maxBorrowedDays;
	}

	public void setMaxBorrowedDays(int maxBorrowedDays) {
		this.maxBorrowedDays = maxBorrowedDays;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> list) {
		this.authors = list;
	}


	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}


	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public static HashMap<String, Book> getBooks() {
		return books;
	}
	
	public static Book getBookByISBN(String isbn) {
		for (String key : books.keySet()) {
			if (key.equals(isbn)) {
				return books.get(key);
			}
		}
		
		return null;
	}
	
	public BookCopy getBookCopy() {
		if (this.bookCopies.size() == 0) {
			return null;
		}
		
		BookCopy copy = this.bookCopies.get(0);
		this.bookCopies.remove(0);
		
		return copy;
	}
	
	public static void saveBook(Book book) {
		DataAccessFacade.getInstance().saveBook(book);
	}
	
	public static HashMap<String, Book> getAllBooks() {
		return DataAccessFacade.getInstance().readBooksMap();
	}

	public static void setBooks(HashMap<String, Book> books) {
		Book.books = books;
	}
}
