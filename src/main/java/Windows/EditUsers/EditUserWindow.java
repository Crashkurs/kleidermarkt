package Windows.EditUsers;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import de.mwilhelm.kleidermarkt.Action.EventNames;
import Entities.User;

public class EditUserWindow extends JFrame {
	
	private JLabel userNameLabel, userNumberLabel, userPercentLabel;
	
	private JTextField userNameField, userNumberField;
	
	private JComboBox<Float> userPercentButton;
	
	private JButton save, cancel;
	
	private User user;
	
	private JTable table;

	public EditUserWindow(User user, ActionListener al)
	{
		super();
		setTitle("Editiere Verk�ufer");
		setLayout(null);
		setBounds(300, 300, 450, 350);
		
		this.user = user;
		
		userNameLabel = new JLabel("Verk�ufer");
		userNameLabel.setBounds(20, 20, 110, 20);
		add(userNameLabel);
		
		userNumberLabel = new JLabel("Verkaufsnummer");
		userNumberLabel.setBounds(20, 50, 110, 20);
		add(userNumberLabel);
		
		userPercentLabel = new JLabel("Prozentabgabe");
		userPercentLabel.setBounds(20, 80, 110, 20);
		add(userPercentLabel);
		
		userNameField = new JTextField(this.user.getVerkufer());
		userNameField.setBounds(140, 20, 100, 20);
		add(userNameField);
		
		userNumberField = new JTextField("" + this.user.getVerkaufsnummer());
		userNumberField.setBounds(140, 50, 100, 20);
		add(userNumberField);
		
		userPercentButton = new JComboBox(User.prozentAbgaben);
		userPercentButton.setBounds(140, 80, 80, 20);
		add(userPercentButton);
		
		int selectedItem = 0;
		for(Float f : User.prozentAbgaben)
		{
			if(f == this.user.getAbgabe())
				break;
			selectedItem++;
		}
		userPercentButton.setSelectedIndex(selectedItem);
		
		save = new JButton("Speichern");
		save.setBounds(20, 110, 100, 30);
		save.setActionCommand(EventNames.saveEditedUser);
		save.addActionListener(al);
		add(save);
		
		cancel = new JButton("Abbrechen");
		cancel.setBounds(300, 110, 100, 30);
		cancel.setActionCommand(EventNames.cancelEditedUser);
		cancel.addActionListener(al);
		add(cancel);
		
		String header[] = {"Verkäufe"};
		String content[][] = new String[user.getVerkaufsListe().isEmpty() ? 1 : user.getVerkaufsListe().size()][1];
		content[0][0] = "Leere Liste";
		int counter = 0;
		for(Float f : user.getVerkaufsListe())
		{
			content[counter][0] = "" + f.floatValue() + "€";
			counter++;
		}
		table = new JTable(content, header);
		table.setEnabled(false);
		JScrollPane panel = new JScrollPane(table);
		panel.setFocusable(false);
		panel.setBounds(80, 150, 100, 150);
		add(panel);
	}
	
	public User getEditedUser()
	{
		int verkaufsNummer;
		try{
			verkaufsNummer = Integer.parseInt(userNumberField.getText());
		}catch(NumberFormatException ex)
		{
			return null;
		}
		String userName = userNameField.getText();
		User user2 = new User(verkaufsNummer, userName);
		user2.setAbgabe((float) userPercentButton.getSelectedItem());
		user2.setVerkaufsListe(user.getVerkaufsListe());
		return user2;
	}
}
