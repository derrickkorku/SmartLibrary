package book_mgmt;

public class BookController {
	public static Book getBookByISBN(String isbn) {
		return Book.getBookByISBN(isbn);
	}
}