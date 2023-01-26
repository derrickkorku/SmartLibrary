package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import book.BookVisual;
import book_mgmt.Author;
import book_mgmt.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class bookController  {

	
	private Book book;
	private BookVisual view;
	
	
	
	public bookController() {
		this.view = BookVisual.getInstance();
	}
	
	public void addBook(String title) {
		
		DataAccess da = new DataAccessFacade();
		int maxDays = Integer.parseInt( this.view.maxBorrowData.getText());
		Book b = new Book(this.view.bookNameText.getText(), this.view.isbnText.getText(), maxDays,null);
		da.saveBook(b);
	}
	
	public ArrayList<Book> updateDataBook() {
		
		DataAccess da = new DataAccessFacade();
		
		HashMap<String, Book> df = da.readBooksMap();
		ArrayList<Book> bookList = new ArrayList<>();
		
		for (Map.Entry<String, Book> book : df.entrySet()) {
		bookList.add(book.getValue());
		}
	return bookList;
	}
	public Book searchBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		
		HashMap<String, Book> df = da.readBooksMap();
		Book bookSearch;
		  if(df.containsKey(isbn)) {
			  bookSearch= df.get(isbn);
			  return bookSearch;
		  }
		  return null;
		
	}



}
