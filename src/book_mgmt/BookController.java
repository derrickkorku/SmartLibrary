package book_mgmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {
	private BookForm view = BookForm.getInstance();
	
	public static Book getBookByISBN(String isbn) {
		return Book.getBookByISBN(isbn);
	}
	
	public static BookCopy getBookCopy(Book book) {
		return book.getBookCopy();
	}
	
	public void addBook(String title, List<Author> listAuthor2) {
		int maxDays = Integer.parseInt(this.view.maxBorrowData.getText());
		Book b = new Book(this.view.bookNameText.getText(), this.view.isbnText.getText(), maxDays, listAuthor2);
		Book.saveBook(b);
	}

	public static ArrayList<Book> getUpdatedBookList() {
		HashMap<String, Book> df = Book.getAllBooks();
		ArrayList<Book> bookList = new ArrayList<>();

		for (Map.Entry<String, Book> book : df.entrySet()) {
			bookList.add(book.getValue());
		}
		return bookList;
	}

	public static Book searchBook(String isbn) {
		return Book.getBookByISBN(isbn);
	}

	public static void addBookCopy(String isbn, int copyNum) throws Exception {
		if (isbn.equals("") || copyNum == 0) {
			throw new Exception("Incorrect value of ISBN or Book copy.");
		}

		Book b = searchBook(isbn);
		b.addCopy();
	}
}
