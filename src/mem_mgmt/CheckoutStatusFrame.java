package mem_mgmt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import checkout.MemberCheckoutRecord;
import checkout.MemberCheckoutRecordController;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CheckoutStatusFrame extends JFrame {
	private static final long serialVersionUID = -2820099428194667393L;
	private JPanel contentPane;
	private JTextField tfSearchText;
	private JTable table;
	DefaultTableModel checkoutModel;

	/**
	 * Create the frame.
	 */
	public CheckoutStatusFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 11, 721, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter ISBN:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(6, 23, 107, 14);
		panel.add(lblNewLabel);

		tfSearchText = new JTextField();
		tfSearchText.setBounds(125, 20, 265, 20);
		panel.add(tfSearchText);
		tfSearchText.setColumns(10);

		JButton btnSearchButton = new JButton("Search");
		btnSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSearchOperation();
			}
		});
		btnSearchButton.setBounds(400, 19, 89, 23);
		panel.add(btnSearchButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 72, 721, 189);
		contentPane.add(scrollPane);

		checkoutModel = new DefaultTableModel();
		checkoutModel.setColumnIdentifiers(getCheckoutModelColumns());
		table = new JTable();
		table.setModel(checkoutModel);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Print in console");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performPrintOperation();
			}
		});
		btnNewButton.setBounds(573, 269, 173, 23);
		contentPane.add(btnNewButton);
	}

	private String[] getCheckoutModelColumns() {
		return new String[] {
				"Title", "ISBN", "Copy Number", "Checked Out Date", "Return Date", "Member Id", "Member Name", "Status"
		};
	}

	private void performSearchOperation() {
		try {
			// ISBN: 23-11451
			checkoutModel.setRowCount(0);
			String searchText = tfSearchText.getText();
			if (searchText.trim().length() <= 0) {
				showMessage("Please enter an ISBN first.");
				return;
			}

			List<MemberCheckoutRecord> mcrList = MemberCheckoutRecordController
					.getMemberRecordsByISBN(searchText.trim());
			for (MemberCheckoutRecord record : mcrList) {
				String overDueStatus = record.getCheckoutEntry().isOverdue() ? "Not Overdue" : "Overdue";
				String[] aRow = {
						record.getCheckoutEntry().getBookCopy().getBook().getTitle(),
						record.getCheckoutEntry().getBookCopy().getBook().getIsbn(),
						String.valueOf(record.getCheckoutEntry().getBookCopy().getCopyNum()),
						record.getCheckoutEntry().getCheckoutDate().toString(),
						record.getCheckoutEntry().getDueDate().toString(),
						record.getMember().getMemberId(),
						record.getMember().getName(), overDueStatus
				};

				checkoutModel.addRow(aRow);
			}
		} catch (Exception ex) {
			showMessage(ex.getLocalizedMessage());
		}
	}

	private void performPrintOperation() {
		if (checkoutModel.getRowCount() == 0) {
			showMessage("Sorry, No records to print.");
			return;
		}

		StringBuffer buffer = new StringBuffer();
		String topOrBottom = "";
		// Print Headers
		for (int col = 0; col < table.getColumnCount(); col++) {
			topOrBottom += "|" + rightPad("-", 50, "-") + "|";
			buffer.append("|" + rightPad(table.getColumnName(col), 50, " ") + "|");
		}
		// Print Records
		buffer.append("\n");
		buffer.append(topOrBottom);
		buffer.append("\n");
		for (int row = 0; row < table.getRowCount(); row++) {
			for (int col = 0; col < table.getColumnCount(); col++) {
				buffer.append("|" + rightPad(table.getValueAt(row, col).toString(), 50, " ") + "|");
			}
			buffer.append("\n");
		}
		buffer.append(topOrBottom);
		buffer.insert(0, "\n");
		buffer.insert(0, topOrBottom);
		System.out.println(buffer.toString());
	}

	private String rightPad(String value, int padLength, String character) {
		if (value.length() > padLength)
			return value.substring(0, padLength);
		for (int i = value.length(); i < padLength; i++)
			value += character;
		return value;
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
