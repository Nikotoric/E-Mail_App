package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewMail extends JDialog {

	private JPanel topPanel, bottomPanel;
	private JLabel from, to;
	private JTextField textFrom;
	private JComboBox<String> comboTo;
	private DefaultComboBoxModel<String> comboModel;
	private JTextArea mail;
	private JButton send;
	private JScrollPane scroll;
	private MailEventAction mea;

	// constructor
	public NewMail(JFrame frame) {

		super(frame, "New mail", false);

		setVisible(false);
		setSize(new Dimension(700, 400));

		createComps();
		createLayout();
		activateComps();

	}

	private void createComps() {

		topPanel = new JPanel();
		bottomPanel = new JPanel();
		from = new JLabel("From");
		to = new JLabel("To");
		textFrom = new JTextField(10);
		comboTo = new JComboBox<>();
		comboModel = new DefaultComboBoxModel<>();
		mail = new JTextArea(20, 20);
		scroll = new JScrollPane(mail, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		send = new JButton("Send");

	}

	private void createLayout() {

		setLayout(new BorderLayout());

		topPanel.setLayout(new GridBagLayout());
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// top panel
		// FROM label , FROM textfield
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.anchor = gbc.FIRST_LINE_END;
		gbc.insets = new Insets(20, 20, 5, 5);
		topPanel.add(from, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.ipadx = 200;
		gbc.anchor = gbc.FIRST_LINE_START;
		topPanel.add(textFrom, gbc);

		// TO label, TO combobox;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.ipadx = 1;
		gbc.insets = new Insets(5, 20, 20, 5);
		gbc.anchor = gbc.FIRST_LINE_END;
		topPanel.add(to, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 5;
		gbc.weightx = 1.0;
		gbc.ipadx = 200;
		gbc.anchor = gbc.FIRST_LINE_START;
		topPanel.add(comboTo, gbc);

		// bottom panel
		// scroll
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 1.0;
		gbc.gridwidth = 10;
		gbc.gridheight = 10;
		gbc.insets = new Insets(5, 20, 20, 5);
		gbc.fill = gbc.BOTH;
		gbc.anchor = gbc.FIRST_LINE_END;
		topPanel.add(scroll, gbc);

		// send button
		gbc.gridx = 10;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		gbc.ipadx = 1;
		gbc.insets = new Insets(5, 20, 20, 20);
		gbc.anchor = gbc.SOUTHEAST;
		gbc.fill = gbc.NONE;
		topPanel.add(send, gbc);

		add(topPanel, BorderLayout.CENTER);

	}

	private void activateComps() {

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (mail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mail can't be empty!!", "Error", JOptionPane.WARNING_MESSAGE);
				} else {

					setVisible(false);
					MailEvent me = new MailEvent(this);

					me.setSender(textFrom.getText());
					me.setReceiver(comboTo.getSelectedItem().toString());
					me.setText(mail.getText());

					mea.storeMail(me);

					comboTo.setSelectedItem(textFrom.getText());

					textFrom.setText("");
					mail.setText("");

				}

			}
		});

	}

	public void setMail(String sender, DefaultComboBoxModel<String> lista) {

		textFrom.setText(sender);
		textFrom.setEditable(false);

		comboTo.setModel(lista);

	}

	public void setMailEventAction(MailEventAction m) {
		mea = m;
	}

}
