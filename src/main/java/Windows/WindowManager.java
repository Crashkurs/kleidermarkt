package Windows;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.mwilhelm.kleidermarkt.Action.ActionDispatcher;
import Windows.EditUsers.EditUsersWindow;
import Windows.History.HistoryWindow;
import Windows.Overview.OverviewWindow;
import Windows.Sells.SellWindow;

public class WindowManager extends JPanel implements ActionListener {
	
	public JPanel activePanel;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	
	private JMenuItem fileMenuOpen;
	
	private JMenuItem fileMenuSave;
	
	private JMenuItem fileMenuPrint;
	
	private JMenuItem fileMenuExit;
	
	private JMenuItem fileMenuExport;
	
	private JMenu viewMenu;
	
	private JMenuItem viewMenuStart;
	
	private JMenuItem viewMenuAdd;
	
	private JMenuItem viewMenuEdit;
	
	private JMenuItem viewMenuHistory;
	
	private HashMap<JMenuItem, String> actionMap;
	
	
	private ActionDispatcher dispatcher;
	
	public WindowManager()
	{
		super();
		setLayout(new GridLayout());
		
		actionMap = new HashMap<JMenuItem, String>();
		dispatcher = new ActionDispatcher();
	
		menuBar = new JMenuBar();
		
		
		fileMenu = new JMenu("Datei");
		
		fileMenuOpen = new JMenuItem("Datei �ffnen");
		fileMenuOpen.addActionListener(this);
		actionMap.put(fileMenuOpen, "openFile");
		fileMenu.add(fileMenuOpen);
		
		fileMenuSave = new JMenuItem("Datei speichern");
		fileMenuSave.addActionListener(this);
		actionMap.put(fileMenuSave, "saveFile");
		fileMenu.add(fileMenuSave);
		
		fileMenuPrint = new JMenuItem("Datei drucken");
		fileMenuPrint.addActionListener(this);
		actionMap.put(fileMenuPrint, "printFile");
		fileMenu.add(fileMenuPrint);
		
		fileMenuExport = new JMenuItem("Nach Excel exportieren");
		fileMenuExport.addActionListener(this);
		actionMap.put(fileMenuExport, "exportFile");
		fileMenu.add(fileMenuExport);
		
		fileMenuExit = new JMenuItem("Programm beenden");
		fileMenuExit.addActionListener(this);
		actionMap.put(fileMenuExit, "exit");
		fileMenu.add(fileMenuExit);
		
		menuBar.add(fileMenu);
		
		viewMenu = new JMenu("Ansicht");
		
		viewMenuStart = new JMenuItem("�bersicht");
		viewMenuStart.addActionListener(this);
		actionMap.put(viewMenuStart, "viewStart");
		viewMenu.add(viewMenuStart);
		
		viewMenuAdd = new JMenuItem("Verkauf hinzuf�gen");
		viewMenuAdd.addActionListener(this);
		actionMap.put(viewMenuAdd, "viewAdd");
		viewMenu.add(viewMenuAdd);
		
		viewMenuEdit = new JMenuItem("Verk�ufer verwalten");
		viewMenuEdit.addActionListener(this);
		actionMap.put(viewMenuEdit, "viewEdit");
		viewMenu.add(viewMenuEdit);
		
		viewMenuHistory = new JMenuItem("R�ckg�ngig machen");
		viewMenuHistory.addActionListener(this);
		actionMap.put(viewMenuHistory, "viewHistory");
		viewMenu.add(viewMenuHistory);
		
		menuBar.add(viewMenu);
	}
	
	public void setActivePanel(BaseWindow window)
	{
		if(activePanel != null)
		{
			activePanel.setVisible(false);
			this.remove(activePanel);
		}
		this.add(window);
		this.activePanel = window;
		repaint();
	}
	
	public JMenuBar getMenu()
	{
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem item = (JMenuItem)event.getSource();
		try {
			getClass().getMethod(actionMap.get(item)).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openFile()
	{
		System.out.println("�ffne Datei");
	}
	
	public void saveFile()
	{
		System.out.println("Speichere Datei");
	}
	
	public void printFile()
	{
		System.out.println("Drucke Datei");
	}
	
	public void exportFile()
	{
		System.out.println("Exportiere Datei nach Excel");
	}
	
	public void exit()
	{
		System.exit(0);
	}
	
	public void viewStart()
	{
		System.out.println("Zeige Startansicht");
		setActivePanel(new OverviewWindow());
	}
	
	public void viewAdd()
	{
		System.out.println("Zeige Ansicht f�r Verk�ufe hinzuf�gen");
		setActivePanel(new SellWindow(dispatcher));
	}
	
	public void viewEdit()
	{
		System.out.println("Zeige Ansicht f�r Verk�ufer-Verwaltung");
		setActivePanel(new EditUsersWindow(dispatcher));
	}
	
	public void viewHistory()
	{
		System.out.println("Zeige Ansicht f�r letzte Verk�ufe");
		setActivePanel(new HistoryWindow(dispatcher));
	}
}
