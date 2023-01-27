package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book_mgmt.Author;
import book_mgmt.Book;
import book_mgmt.BookForm;
import dataaccess.DataAccessFacade;

public class bookController {
	private BookForm view = BookForm.getInstance();

	public void addBook(String title, List<Author> listAuthor2) {
		int maxDays = Integer.parseInt(this.view.maxBorrowData.getText());
		Book b = new Book(this.view.bookNameText.getText(), this.view.isbnText.getText(), maxDays, listAuthor2);
		Book.saveBook(b);
	}

	public ArrayList<Book> updateDataBook() {
		HashMap<String, Book> df = DataAccessFacade.getInstance().readBooksMap();
		ArrayList<Book> bookList = new ArrayList<>();

		for (Map.Entry<String, Book> book : df.entrySet()) {
			bookList.add(book.getValue());
		}
		return bookList;
	}

	public Book searchBook(String isbn) {
		return Book.getBookByISBN(isbn);
	}

	public void addBookCopy(String isbn, int copyNum) throws Exception {
		if (isbn.equals("") || copyNum == 0) {
			throw new Exception("Incorrect value of ISBN or Book copy.");
		}

		Book b = searchBook(isbn);
		b.addCopy();
	}

}
