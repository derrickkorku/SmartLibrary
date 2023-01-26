package book;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.bookController;
import book_mgmt.Book;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class BookVisual extends JFrame {

	private JPanel contentPane;
	public  JButton btnAddBook;
	public JTextField bookNameText;
	public JTextField isbnText;
	public JTextField maxBorrowData;
	public static BookVisual Instance = null;
	private boolean isInitialized = true;
	private JTable table;
	private JScrollPane scrollPane;
	Object[] row = new Object[4];
	private ArrayList<Book> bookList = new ArrayList<>();
	DefaultTableModel modelTableBook;
	private JButton btnUpdateList;
	private JTextField searchIsbnField;
	private JButton btnSearchBook;
	private JLabel lblNewLabel_3;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	
	public static BookVisual getInstance() {
		
		if(Instance == null) {
			Instance = new BookVisual();
		}
		return Instance;
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookVisual frame =  getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookVisual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnAddBook = new JButton("add");

		 btnAddBook.setBounds(58, 159, 85, 21);
		contentPane.add(btnAddBook);
		
		bookNameText = new JTextField();
		bookNameText.setBounds(145, 40, 96, 19);
		contentPane.add(bookNameText);
		bookNameText.setColumns(10);
		
		isbnText = new JTextField();
		isbnText.setBounds(145, 69, 96, 19);
		contentPane.add(isbnText);
		isbnText.setColumns(10);
		
		maxBorrowData = new JTextField();
		maxBorrowData.setBounds(145, 98, 96, 19);
		contentPane.add(maxBorrowData);
		maxBorrowData.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(45, 43, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN");
		lblNewLabel_1.setBounds(45, 72, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MaxDays");
		lblNewLabel_2.setBounds(45, 101, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 41, 452, 206);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.text);
		Object[] column = {"Title","ISBN","MaxDays","Author"};
	
		modelTableBook = new DefaultTableModel();
		modelTableBook.setColumnIdentifiers(column);
		table.setModel(modelTableBook);
		
		
		scrollPane.setViewportView(table);
		
		btnUpdateList = new JButton("Update List");
		btnUpdateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookController bookCtr = new bookController();
				bookList=	bookCtr.updateDataBook();
				addRowBook(bookList);
			}
		});
		btnUpdateList.setBounds(153, 159, 85, 21);
		contentPane.add(btnUpdateList);
		
		searchIsbnField = new JTextField();
		searchIsbnField.setBounds(333, 273, 201, 19);
		contentPane.add(searchIsbnField);
		searchIsbnField.setColumns(10);
		
		btnSearchBook = new JButton("Search");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowsCount = modelTableBook.getRowCount(); 
				for (int i =rowsCount-1 ;i>=0;i--) modelTableBook.removeRow(i);
				bookController bookCtr = new bookController();
				Book bookSearch=bookCtr.searchBook(searchIsbnField.getText());
				if (bookSearch!=null) {
					row[0] = bookSearch.getTitle();
					
					row[1] = bookSearch.getIsbn();
					row[2] = bookSearch.getMaxBorrowedDays();
					row[3] = "";
					modelTableBook.addRow(row);
				}else {
					JOptionPane.showMessageDialog(null, "the ISBN book its not register");
				}
			}
		});
		btnSearchBook.setBounds(544, 272, 120, 20);
		contentPane.add(btnSearchBook);
		
		lblNewLabel_3 = new JLabel("Search By Isbn");
		lblNewLabel_3.setBounds(181, 276, 128, 13);
		contentPane.add(lblNewLabel_3);
		
		
//		
		
		 btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookController bookCtr = new bookController();
				bookCtr.addBook(bookNameText.getText());
				bookList=	bookCtr.updateDataBook();
				addRowBook(bookList);
				
			}
		});
	}
	
	public void addRowBook(ArrayList<Book>  bookList) {
		int rowsCount = modelTableBook.getRowCount(); 
		for (int i =rowsCount-1 ;i>=0;i--) modelTableBook.removeRow(i);
		for ( Book book : bookList) {
			if(book!= null) {
					row[0] = book.getTitle();
					
					row[1] = book.getIsbn();
			row[2] = book.getMaxBorrowedDays();
			row[3] = "";
			modelTableBook.addRow(row);
			}
		}
		
	}
	
	
	
}
