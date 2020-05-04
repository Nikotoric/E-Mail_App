package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class MailList extends JDialog implements MailEventAction {

	private JPanel topPanel, midPanel;
	private JLabel label;
	private JTextArea txtList;
	private JTextField txtMail;
	private JButton btnClose;
	private JScrollPane scroll;

	public MailList(JFrame frame) {

		super(frame, "All mails", false);

		createComps();
		activateComps();

	}

	private void createComps() {

		setVisible(false);
		setSize(400, 400);
		setResizable(false);

		setLayout(new BorderLayout());

		topPanel = new JPanel();
		midPanel = new JPanel();

		label = new JLabel("Mails for: ");
		txtList = new JTextArea(20, 20);
		txtMail = new JTextField(15);
		btnClose = new JButton("Close");
		scroll = new JScrollPane(txtList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		topPanel.setLayout(new GridBagLayout());
		midPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(30, 30, 30, 0);
		topPanel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(30, 0, 0, 30);
		topPanel.add(txtMail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbc.ipadx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 20, 0, 20);
		gbc.fill = gbc.BOTH;
		midPanel.add(scroll, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = gbc.NONE;
		midPanel.add(btnClose, gbc);

		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);

	}

	private void activateComps() {

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CloseAction();

			}
		});

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {

				CloseAction();
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void CloseAction() {

		setVisible(false);
		txtList.setText("");
		txtMail.setText("");

	}

	@Override
	public void storeMail(MailEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listMail(ListEvent le) {

		setVisible(true);

		txtMail.setText(le.getUser());

		txtMail.setEditable(false);

		txtList.append("\n********** Sent mail's **********");
		for (String st : le.getSentMail()) {

			txtList.append("\n" + st + "\n");
		}

		txtList.append("\n********** Received mail's **********");
		for (String st : le.getReceivedMail()) {

			txtList.append("\n" + st + "\n");

		}

	}

}
