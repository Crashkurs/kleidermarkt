import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Entities.User;
import Windows.WindowManager;
import Windows.Overview.OverviewWindow;


public class App extends JFrame {
	
	public static void main(String[] args)
	{
		App app = new App();
	}
	
	private App()
	{
		setLayout(null);
		setTitle("Kleidermarkt");
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WindowManager manager = null;
		manager = new WindowManager();
		manager.setBounds(0, 0, 800, 600);
		manager.setActivePanel(new OverviewWindow());
		add(manager);
			
		setJMenuBar(manager.getMenu());
		setVisible(true);
		
		List<User> users = new LinkedList<User>();
		users.add(new User(0));
		
	}
}
