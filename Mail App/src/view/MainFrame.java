package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.User;

public class MainFrame extends JFrame implements MailEventAction {

	private JMenuBar meni;
	private JMenu fileMenu;
	private JMenuItem saveAs, open, exit;
	private JPanel leftPanel, rightPanel;

	private JLabel userName, mailAdress, userId;
	private JTextField textUserName, textMailAdress, textUserId;
	private static int id = 1;
	private JButton confirm;
	private JLabel selectMailbox;
	private JComboBox<String> comboSelection;
	private JButton composeMail, listAll;
	private JPanel mainPanel;
	private DefaultComboBoxModel<String> boxModel;

	private NewMail newMail;
	private MailList mailList;
	private Controller controler;
	private ListEvent listEvent;
	private MailEventAction mea;

	private JFileChooser chooser;
	private FileNameExtensionFilter filter;

	public MainFrame() {

		super("Email app");
		setSize(new Dimension(700, 500));
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createComps();
		borders();
		createLayout();
		activate();
		openDefaultUser();

	}

	private void activate() {

		saveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int opt = chooser.showSaveDialog(new JFrame());

				if (opt == JFileChooser.APPROVE_OPTION) {

					saveUser();
				}

			}
		});

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int opt = chooser.showOpenDialog(new JFrame());

				if (opt == JFileChooser.APPROVE_OPTION) {

					openUser();
				}

			}
		});

		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controler.newUser(textUserName.getText(), textMailAdress.getText(),
						Integer.parseInt(textUserId.getText()));

				boxModel.addElement(controler.getMail(controler.getUser(textMailAdress.getText())));
				boxModel.setSelectedItem(controler.getMail(controler.getUser(textMailAdress.getText())));

				textUserName.setText("");
				textMailAdress.setText("");
				id++;
				textUserId.setText("" + id + "");
			}

		});

		composeMail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				newMail.setVisible(true);
				newMail.setMail(boxModel.getSelectedItem().toString(), boxModel);

			}
		});

		listAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ListEvent le = new ListEvent(this);

				le.setUser(controler.getUser(comboSelection.getSelectedItem().toString()).getMail());
				le.setSentMail(controler.getSentList(controler.getUser(comboSelection.getSelectedItem().toString())));
				le.setReceivedMail(
						controler.getReceivedList(controler.getUser(comboSelection.getSelectedItem().toString())));

				mailList.listMail(le);
			}

		});

	}

	private void createComps() {

		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		userName = new JLabel("Username: ");
		textUserName = new JTextField(10);
		mailAdress = new JLabel("Mail address: ");
		textMailAdress = new JTextField(10);
		userId = new JLabel("User id: ");
		textUserId = new JTextField(10);
		textUserId.setText("" + id + "");
		textUserId.setEditable(false);
		confirm = new JButton("Confirm");

		selectMailbox = new JLabel("Select mailbox:");
		composeMail = new JButton("Compose mail");
		listAll = new JButton("List all mails");
		comboSelection = new JComboBox<>();

		meni = new JMenuBar();
		fileMenu = new JMenu("File Menu");
		saveAs = new JMenuItem("Save as..");
		open = new JMenuItem("Open ...");
		exit = new JMenuItem("Exit");

		boxModel = new DefaultComboBoxModel<>();
		newMail = new NewMail(new JFrame());
		newMail.setMailEventAction(this);
		mailList = new MailList(new JFrame());
		comboSelection.setModel(boxModel);
		boxModel.addElement("Selection...");
		setListEvent(this);
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("user Database", "usr");
		controler = new Controller();
		chooser.setFileFilter(filter);

	}

	private void createLayout() {

		setLayout(new BorderLayout());
		mainPanel.setLayout(new GridLayout(1, 2));

		leftPanel.setLayout(new GridBagLayout());
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		fileMenu.add(open);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		meni.add(fileMenu);

		add(meni, BorderLayout.NORTH);

		// left panel //

		// label and textfield - USERNAME
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 20, 20, 10);
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(userName, gbc);

		gbc.gridx = 1;
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(textUserName, gbc);

		// label and textfield - MAIL ADDRESS
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(mailAdress, gbc);

		gbc.gridx = 1;
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(textMailAdress, gbc);

		// label and textfield - USER ID
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(userId, gbc);

		gbc.gridx = 1;
		gbc.anchor = gbc.FIRST_LINE_START;
		leftPanel.add(textUserId, gbc);

		// button confirm
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(150, 0, 0, 0);
		leftPanel.add(confirm, gbc);

		mainPanel.add(leftPanel);

		// right panel //
		// select mailBox label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = gbc.FIRST_LINE_START;
		rightPanel.add(selectMailbox, gbc);

		// comboBox selection
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.anchor = gbc.FIRST_LINE_START;
		rightPanel.add(comboSelection, gbc);

		// compose mail button
		gbc.gridy = 2;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(20, 0, 10, 0);
		rightPanel.add(composeMail, gbc);

		// list all mails button
		gbc.gridy = 3;
		gbc.anchor = gbc.CENTER;
		gbc.insets = new Insets(150, 0, 0, 0);
		rightPanel.add(listAll, gbc);

		mainPanel.add(rightPanel);

		add(mainPanel, BorderLayout.CENTER);

	}

	public void saveUser() {

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(chooser.getSelectedFile() + ".usr"))) {

			oos.writeObject(controler.getUserDB());
			System.out.println("User's saved successfully..");

		} catch (IOException e) {

			System.out.println("Error writing file ...");

		}

	}

	public void openUser() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()))) {

			ArrayList<User> tempList = new ArrayList<>();

			tempList = (ArrayList<User>) ois.readObject();

			for (User user : tempList) {

				controler.addUsers(user);
				boxModel.addElement(controler.getMail(user));
				boxModel.setSelectedItem(controler.getMail(user));

			}

			System.out.println("Users added successfully ..");

		} catch (Exception e) {

			System.out.println("No user dataBase ...");

		}

	}

	public void openDefaultUser() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(".\\user.usr"))) {

			ArrayList<User> tempList = new ArrayList<>();

			tempList = (ArrayList<User>) ois.readObject();

			for (User user : tempList) {

				controler.addUsers(user);
				boxModel.addElement(controler.getMail(user));
				boxModel.setSelectedItem(controler.getMail(user));

			}

			System.out.println("Users added successfully ..");

		} catch (Exception e) {

			System.out.println("No user dataBase ...");

		}

	}

	private void borders() {

		Border leftTop = BorderFactory.createTitledBorder("New mailBox");
		Border rightTop = BorderFactory.createTitledBorder("Communication");
		Border bottom = BorderFactory.createEmptyBorder(0, 0, 0, 0);

		leftPanel.setBorder(BorderFactory.createCompoundBorder(leftTop, bottom));
		rightPanel.setBorder(BorderFactory.createCompoundBorder(rightTop, bottom));

	}

	public void setListEvent(MailEventAction m) {
		mea = m;
	}

	@Override
	public void storeMail(MailEvent me) {

		controler.addSentMail(controler.getUser(me.getSender()), "To: " + me.getReceiver() + "\n" + me.getText());
		controler.addReceivedMail(controler.getUser(me.getReceiver()), "From: " + me.getSender() + "\n" + me.getText());

	}

	@Override
	public void listMail(ListEvent le) {
		// TODO Auto-generated method stub

	}

}
