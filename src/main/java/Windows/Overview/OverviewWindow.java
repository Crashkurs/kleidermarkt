package Windows.Overview;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Entities.User;
import de.mwilhelm.kleidermarkt.Database.Database;
import Windows.BaseWindow;

public class OverviewWindow extends BaseWindow {
	
	private List<User> users = new LinkedList<User>();
	
	private JTable table;
	
	private JScrollPane scrollPane;
	
	public OverviewWindow()
	{
		super();
		setLayout(null);
		updateUsers(Database.getUsers());
	}
	
	public void updateUsers(List<User> newUsers)
	{
		users = newUsers;
		updateTable();
	}
	
	private void updateTable()
	{
		if(scrollPane != null)
			remove(scrollPane);
		String[] columnNames = new String[users.size()];
		for(int x = 0; x < columnNames.length; x++)
		{
			columnNames[x] = "" + users.get(x).getVerkaufsnummer();
		}
		
		int columnCounter = 0;
		String[][] columnContent = new String[users.size()][300];
		for(int x = 0; x < columnNames.length; x++)
		{
			columnContent[0][x] = users.get(x).getVerkufer();
		}
		for(int x = 1; x < columnNames.length; x++)
		{
			for(int y = 0; y < users.get(x).getVerkaufsListe().size(); y++)
			{
				columnContent[y][x] = "" + users.get(x).getVerkaufsListe().get(y);
			}
		}
		
		table = new JTable(columnContent, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(50, 50, 600, 450);
		add(scrollPane, BorderLayout.CENTER);
		table.setEnabled(false);
	}
}
