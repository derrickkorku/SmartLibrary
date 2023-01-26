package book_mgmt;

import java.io.Serializable;

public class BookCopy implements Serializable{
	private static final long serialVersionUID = 7399647194328362151L;
	private Book book;
	private int copyNum;
	
	BookCopy(int copyNum, Book book){
		this.setCopyNum(copyNum);
		this.book = book;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}

}
