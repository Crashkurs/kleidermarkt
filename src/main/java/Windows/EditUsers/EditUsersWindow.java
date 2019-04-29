package Windows.EditUsers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.mwilhelm.kleidermarkt.Action.Action;
import de.mwilhelm.kleidermarkt.Action.ActionDispatcher;
import de.mwilhelm.kleidermarkt.Action.AddUserAction;
import de.mwilhelm.kleidermarkt.Action.DeleteUserAction;
import de.mwilhelm.kleidermarkt.Action.EditUserAction;
import de.mwilhelm.kleidermarkt.Action.EventNames;
import de.mwilhelm.kleidermarkt.Database.Database;
import Entities.User;
import Table.ClassTableModel;
import Windows.BaseWindow;

public class EditUsersWindow extends BaseWindow implements ActionListener {
	
	private JButton newUser;
	private JButton editUser;
	private JButton deleteUser;
	
	private List<User> users;
	
	private JScrollPane scrollPane;
	private JTable table;
	
	private NewUserWindow newUserWindow;
	
	private EditUserWindow editUserWindow;
	
	private ActionDispatcher dispatcher;
	
	public EditUsersWindow(ActionDispatcher dispatcher)
	{
		super();
		setLayout(null);
		this.dispatcher = dispatcher;
		
		newUser = new JButton("Neu");
		newUser.setActionCommand(EventNames.showCreateUserWindow);
		newUser.setBounds(50, 50, 100, 40);
		newUser.addActionListener(this);
		add(newUser);
		
		editUser = new JButton("Editieren");
		editUser.setBounds(200, 50, 100, 40);
		editUser.setActionCommand(EventNames.showEditUserWindow);
		editUser.addActionListener(this);
		add(editUser);
		
		deleteUser = new JButton("L�schen");
		deleteUser.setBounds(350, 50, 100, 40);
		deleteUser.setActionCommand(EventNames.showDeleteUserWindow);
		deleteUser.addActionListener(this);
		add(deleteUser);
		
		users = Database.getUsers();
		
		updateTable();
	}
	
	private void updateTable()
	{
		if(!users.isEmpty())
		{
			if(scrollPane != null)
				remove(scrollPane);
			String[] columnNames = {"Verkaufsnummer", "Verkäufername"};
			
			String[][] columnContent = new String[users.size()][2];
			for(int x = 0; x < users.size(); x++)
			{
				columnContent[x][0] = "" + users.get(x).getVerkaufsnummer();
				columnContent[x][1] = "" + users.get(x).getVerkufer();
			}
			
			table = new JTable(new ClassTableModel<User>(User.class, users));
			table.setFillsViewportHeight(true);
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 130, 600, 400);
			add(scrollPane);
			table.setFocusable(false);
			table.setRowSelectionAllowed(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command == EventNames.showCreateUserWindow)
		{
			if(newUserWindow != null) {
				newUserWindow.setVisible(false);
				newUserWindow = null;
			}
			newUserWindow = new NewUserWindow(this);
			newUserWindow.setVisible(true);
		}
		else if(command == EventNames.showEditUserWindow)
		{
			int selectedUser = table.getSelectedRow();
			if(selectedUser >= 0)
			{
				User user = users.get(selectedUser);
				if(editUserWindow != null) {
					editUserWindow.setVisible(false);
					editUserWindow = null;
				}
				editUserWindow = new EditUserWindow(user, this);
				editUserWindow.setVisible(true);
			}else{
				System.out.println("Kein Verk�ufer ausgew�hlt");
			}
		}
		else if(command == EventNames.showDeleteUserWindow)
		{
			int selectedUser = table.getSelectedRow();
			if(selectedUser >= 0)
			{
				int n = JOptionPane.showConfirmDialog(
				    this,
				    "Verk�ufer wirklich l�schen?",
				    "Best�tigung",
				    JOptionPane.YES_NO_OPTION);
				if(n == 0)
				{
					Action deleteUser = new DeleteUserAction(users.get(selectedUser));
					dispatcher.addAction(deleteUser);
				}
			}
			
		}else if(command == EventNames.createNewUser)
		{
				User user = newUserWindow.getNewUser();
				Action newUser = new AddUserAction(user);
				dispatcher.addAction(newUser);
				newUserWindow.setVisible(false);
				newUserWindow = null;
		}else if(command == EventNames.saveEditedUser)
		{
			if(editUserWindow != null)
			{
				User user = editUserWindow.getEditedUser();
				EditUserAction editAction = new EditUserAction(user);
				dispatcher.addAction(editAction);
				editUserWindow.setVisible(false);
				editUserWindow = null;
			}else{
				
			}
		}else if(command == EventNames.cancelEditedUser)
		{
			if(editUserWindow != null)
			{
				editUserWindow.setVisible(false);
				editUserWindow = null;
			}
		}
		
		users = Database.getUsers();
		dispatcher.dispatch();
		updateTable();
	}
}
