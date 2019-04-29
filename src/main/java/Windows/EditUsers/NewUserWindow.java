package Windows.EditUsers;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.mwilhelm.kleidermarkt.Action.EventNames;
import Entities.User;

public class NewUserWindow extends JFrame {
	
	private JLabel userNumber;
	private JLabel userName;
	
	private JTextField userNumberField;
	private JTextField userNameField;
	
	private JButton createUser;
	private JButton cancel;
	
	public NewUserWindow(ActionListener al)
	{
		super();
		setLayout(null);
		setTitle("Erstelle neuen Verk�ufer");
		setBounds(300, 300, 250, 200);
		
		userNumber = new JLabel("Verkaufsnummer");
		userNumber.setBounds(20, 20, 100, 20);
		add(userNumber);
		
		userName = new JLabel("Verk�ufername");
		userName.setBounds(20, 50, 100, 20);
		add(userName);
		
		userNumberField= new JTextField();
		userNumberField.setBounds(120, 20, 80, 20);
		add(userNumberField);
		
		userNameField= new JTextField();
		userNameField.setBounds(120, 50, 80, 20);
		add(userNameField);
		
		createUser = new JButton("Erstellen");
		createUser.setActionCommand(EventNames.createNewUser);
		createUser.setBounds(60,  100, 90, 20);
		createUser.addActionListener(al);
		add(createUser);

		cancel = new JButton("Abbrechen");
		cancel.setActionCommand(EventNames.cancelNewUser);
		cancel.setBounds(60,  100, 90, 20);
		cancel.addActionListener(al);
		add(cancel);
	}
	
	public User getNewUser()
	{
		int number;
		try{
			number = Integer.parseInt(userNumberField.getText());
		}catch(NumberFormatException e)
		{
			return null;
		}
		String name = userNameField.getText();
		User user = new User(number);
		user.setVerkaufsName(name);
		
		return user;
	}
}
