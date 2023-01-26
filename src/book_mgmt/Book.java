package book_mgmt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
	private static final long serialVersionUID = -8563941648780540988L;
	private String isbn;
	private String title;
	private int maxBorrowedDays;
	private List<Author> authors;
	private List<BookCopy> bookCopies = new ArrayList<BookCopy>();
	
	

	public Book(String title, String isbn, int maxBorrowedDays, List<Author> list) {
		this.title = title;
		this.isbn = isbn;
		this.setMaxBorrowedDays(maxBorrowedDays);
		this.setAuthors(list);
	}

	public Book addCopy() {
		BookCopy copy = new BookCopy(this.bookCopies.size(), this);
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
}
