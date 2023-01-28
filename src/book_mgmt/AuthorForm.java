package book_mgmt;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorForm extends JFrame {
	private static final long serialVersionUID = -2125471242977842365L;
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneField;
	private JTextField bioField;
	public static AuthorForm Instance = null;
	private List<ActionListener> buttonActionListeners;

	public Author author;

	public static AuthorForm getInstance() {
		Instance = new AuthorForm();
		return Instance;
	}

	/**
	 * Create the frame.
	 */
	public AuthorForm() {
		buttonActionListeners = new ArrayList<>();
		setBounds(100, 100, 266, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		firstNameField = new JTextField();
		firstNameField.setBounds(41, 41, 96, 19);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);

		lastNameField = new JTextField();
		lastNameField.setBounds(41, 84, 96, 19);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(41, 136, 96, 19);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		bioField = new JTextField();
		bioField.setBounds(41, 178, 96, 19);
		contentPane.add(bioField);
		bioField.setColumns(10);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(41, 18, 65, 13);
		contentPane.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(41, 70, 65, 13);
		contentPane.add(lblLastName);

		JLabel lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setBounds(41, 113, 45, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Short Bio");
		lblNewLabel_2.setBounds(41, 165, 45, 13);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("add Author");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				author = new Author(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), null,
						bioField.getText());
				dispose();
				for (ActionListener listener : buttonActionListeners) {
					listener.actionPerformed(e);
				}
			}
		});
		btnNewButton.setBounds(41, 235, 85, 21);
		contentPane.add(btnNewButton);
	}

	public void addMyButtonActionListener(ActionListener a) {
		if (!buttonActionListeners.contains(a))
			buttonActionListeners.add(a);
	}

	public Author getAuthor() {
		return author;
	}

}
