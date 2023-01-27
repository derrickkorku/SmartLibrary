package checkout;

import java.io.Serializable;
import java.time.LocalDate;

import book_mgmt.BookCopy;

public class CheckOutEntry implements Serializable{
	private static final long serialVersionUID = 7958187157566045346L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	private boolean isOverdue;
	
	
	public CheckOutEntry(BookCopy bookCopy){
		this.checkoutDate = LocalDate.now();
		this.dueDate =	LocalDate.now().plusDays(bookCopy.getBook().getMaxBorrowedDays());
		this.setBookCopy(bookCopy);
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = LocalDate.now().isAfter(this.getDueDate());
	}
}
