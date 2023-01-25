package book_mgmt;

public class Book {
	private String isbn;
	private String title;
	private int maxBorrowedDays;
	
	public Book(String title, String isbn, int maxBorrowedDays) {
		this.title = title;
		this.isbn = isbn;
		this.setMaxBorrowedDays(maxBorrowedDays);
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
}
