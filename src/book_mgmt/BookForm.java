package book_mgmt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class BookForm extends JFrame implements ActionListener {
	private static final long serialVersionUID = 3759540471797094414L;
	private JPanel contentPane;
	public JButton btnAddBook;
	public JTextField bookNameText;
	public JTextField isbnText;
	public JTextField maxBorrowData;
	private JTable table;
	private JScrollPane scrollPane;
	Object[] row = new Object[5];
	Object[] row2 = new Object[6];
	private ArrayList<Book> bookList = new ArrayList<>();
	DefaultTableModel modelTableBook;
	DefaultTableModel modelTableAuthors;
	private JButton btnUpdateList;
	private JTextField searchIsbnField;
	private JButton btnSearchBook;
	private JLabel lblNewLabel_3;
	private JTable table_1;
	private AuthorForm instanceAuthor;
	private List<Author> listAuthor = new ArrayList<>();
	private JButton btnAddCopy;
	private JButton btnAddAuthor = new JButton("Add Author");
	private static BookForm instance = new BookForm();
	public JComboBox daysComboBox;

	/**
	 * Create the frame.
	 */
	private BookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAddBook = new JButton("add");

		btnAddBook.setBounds(45, 272, 85, 21);
		contentPane.add(btnAddBook);

		bookNameText = new JTextField();
		bookNameText.setBounds(145, 40, 96, 19);
		contentPane.add(bookNameText);
		bookNameText.setColumns(10);

		isbnText = new JTextField();
		isbnText.setBounds(145, 69, 96, 19);
		contentPane.add(isbnText);
		isbnText.setColumns(10);

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
		scrollPane.setBounds(310, 41, 452, 206);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(SystemColor.text);
		Object[] column = { "Title", "ISBN", "MaxDays", "Author", "copies" };
		Object[] columnAuthor = { "Firstname", "LastName", "phone", "Address", "Bio" };

		modelTableBook = new DefaultTableModel();
		modelTableAuthors = new DefaultTableModel();
		modelTableBook.setColumnIdentifiers(column);
		modelTableAuthors.setColumnIdentifiers(columnAuthor);
		table.setModel(modelTableBook);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 149, 290, 67);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		scrollPane.setViewportView(table);
		table_1.setModel(modelTableAuthors);
		btnUpdateList = new JButton("Update List");
		btnUpdateList.addActionListener(this);
		btnUpdateList.setBounds(156, 272, 109, 21);
		contentPane.add(btnUpdateList);

		searchIsbnField = new JTextField();
		searchIsbnField.setBounds(403, 273, 201, 19);
		contentPane.add(searchIsbnField);
		searchIsbnField.setColumns(10);

		btnSearchBook = new JButton("Search");
		btnSearchBook.addActionListener(this);
		btnSearchBook.setBounds(614, 272, 120, 20);
		contentPane.add(btnSearchBook);

		lblNewLabel_3 = new JLabel("Search By Isbn");
		lblNewLabel_3.setBounds(319, 276, 74, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Author List");
		lblNewLabel_4.setBounds(94, 127, 67, 13);
		contentPane.add(lblNewLabel_4);

		JButton btnAddAuthor = new JButton("add Author");
		btnAddAuthor.addActionListener(this);
		btnAddAuthor.setBounds(112, 226, 129, 21);
		contentPane.add(btnAddAuthor);

		btnAddCopy = new JButton("Add copy");
		btnAddCopy.addActionListener(this);
		btnAddCopy.setBounds(744, 272, 85, 21);
		contentPane.add(btnAddCopy);

		daysComboBox = new JComboBox();
		daysComboBox.setBounds(147, 99, 94, 22);
		daysComboBox.addItem("21");
		daysComboBox.addItem("7");

		contentPane.add(daysComboBox);

		btnAddBook.addActionListener(this);

		bookList = BookController.getUpdatedBookList();
		addRowBook(bookList);
	}

	public static BookForm getInstance() {
		return instance;
	}

	public void addRowAuthor(List<Author> listAuthor2) {
		int rowsCount = modelTableAuthors.getRowCount();
		for (int i = rowsCount - 1; i >= 0; i--)
			modelTableAuthors.removeRow(i);
		for (Author author : listAuthor2) {
			if (author != null) {
				row2[0] = author.getFirstName();

				row2[1] = author.getLastName();
				row2[2] = author.getPhoneNumber();
				row2[3] = "";
				row2[4] = author.getShortBio();
				modelTableAuthors.addRow(row2);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdateList) {
			this.updateList();
			return;
		}

		if (e.getSource() == btnSearchBook) {
			searchBook();
			return;
		}

		if (e.getSource() == btnAddAuthor) {
			this.addAuthor();
			return;
		}

		if (e.getSource() == btnAddCopy) {
			this.addBookCopy();
			return;
		}

		if (e.getSource() == btnAddBook) {
			this.addBook();
			return;
		}
	}

	private void addBook() {
		if (isbnText.getText().isEmpty() || bookNameText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "ISBN or Title Book cant be empty");
			return;
		}

		(new BookController()).addBook(bookNameText.getText(), listAuthor);

		try {
			BookController.addBookCopy(isbnText.getText(), 1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		bookList = BookController.getUpdatedBookList();
		addRowBook(bookList);
		listAuthor = new ArrayList<>();
		isbnText.setText("");
		bookNameText.setText("");
		int rowsCount = modelTableAuthors.getRowCount();
		for (int i = rowsCount - 1; i >= 0; i--)
			modelTableAuthors.removeRow(i);

		JOptionPane.showMessageDialog(null, "New book added in the table");
	}

	private void addBookCopy() {
		String LastNamesAuthors = "";
		int rowsCount = modelTableBook.getRowCount();
		for (int i = rowsCount - 1; i >= 0; i--)
			modelTableBook.removeRow(i);

		try {
			BookController.addBookCopy(searchIsbnField.getText(), 1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Book bookSearch = BookController.searchBook(searchIsbnField.getText());
		if (bookSearch != null) {
			if (bookSearch.getAuthors() != null)
				for (Author author : bookSearch.getAuthors()) {
					LastNamesAuthors += author.getLastName() + ",";
				}
			;
			row[0] = bookSearch.getTitle();
			row[1] = bookSearch.getIsbn();
			row[2] = bookSearch.getMaxBorrowedDays();
			if (!LastNamesAuthors.isEmpty()) {
				row[3] = LastNamesAuthors;
			} else {
				row[3] = "Anonymus";
			}
			row[4] = bookSearch.getBookCopies().size();
			modelTableBook.addRow(row);
		}
	}

	private void addAuthor() {
		instanceAuthor = AuthorForm.getInstance();
		instanceAuthor.setVisible(true);

		instanceAuthor.addMyButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "My button in the First JPanel Click!");
				listAuthor.add(instanceAuthor.getAuthor());
				addRowAuthor(listAuthor);
			}
		});
	}

	private void searchBook() {
		int rowsCount = modelTableBook.getRowCount();
		for (int i = rowsCount - 1; i >= 0; i--)
			modelTableBook.removeRow(i);
		Book bookSearch = BookController.searchBook(searchIsbnField.getText());
		row[0] = bookSearch.getTitle();
		row[1] = bookSearch.getIsbn();
		row[2] = bookSearch.getMaxBorrowedDays();
		row[3] = "";
		row[4] = bookSearch.getBookCopies().size();
		modelTableBook.addRow(row);
	}

	private void updateList() {
		bookList = BookController.getUpdatedBookList();
		addRowBook(bookList);
	}

	public void addRowBook(ArrayList<Book> bookList) {
		String LastNamesAuthors = "";
		int rowsCount = modelTableBook.getRowCount();
		for (int i = rowsCount - 1; i >= 0; i--)
			modelTableBook.removeRow(i);
		for (Book book : bookList) {
			LastNamesAuthors = "";
			if (book.getAuthors() != null)
				for (Author author : book.getAuthors()) {
					LastNamesAuthors += author.getLastName() + ",";
				}
			if (book != null) {
				row[0] = book.getTitle();
				row[1] = book.getIsbn();
				row[2] = book.getMaxBorrowedDays();

				if (!LastNamesAuthors.isEmpty()) {
					row[3] = LastNamesAuthors;
				} else {
					row[3] = "Anonymus";
				}
				row[4] = book.getBookCopies().size();
				modelTableBook.addRow(row);
			}
		}

	}
}
