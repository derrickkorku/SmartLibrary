package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import book_mgmt.Author;
import book_mgmt.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import visual.BookVisual;

public class bookController  {

	
	private Book book;
	private BookVisual view;
	public List<Author> listAuthor;
	public bookController contBookController;
	public int a ;

	
	
	public bookController() {
		
		this.view = BookVisual.getInstance();
	}
	
	
	
	public bookController(int a) {
		this.a = a;
	}



	public void addBook(String title, List<Author> listAuthor2) {
		DataAccess da = new DataAccessFacade();
		int maxDays = Integer.parseInt( this.view.daysComboBox.getSelectedItem().toString());
		Book b = new Book(this.view.bookNameText.getText(), this.view.isbnText.getText(), maxDays,listAuthor2);
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
	
	public void addBookCopy(String isbn, int copyNum) throws Exception {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		
		if (isbn.equals("") || copyNum == 0) {
			throw new Exception("Incorrect value of ISBN or Book copy.");
		}
		
		Book b = searchBook(isbn);
		if (b==null) {return;}
		b.addCopy();	
		da.saveBook(b);
		
	}



}
