package Windows.Sells;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.mwilhelm.kleidermarkt.Action.ActionDispatcher;
import de.mwilhelm.kleidermarkt.Action.AddSellingAction;
import de.mwilhelm.kleidermarkt.Database.Database;
import Entities.User;
import Windows.BaseWindow;

public class SellWindow extends BaseWindow implements ActionListener {

	private JTextField sellPrice;
	private JLabel sellPriceLabel, sellNumberLabel;
	private JComboBox<Integer> sellNumber;
	private JButton addSell, clearSell;
	
	private ActionDispatcher dispatcher;
	
	public SellWindow(ActionDispatcher dispatcher) {
		super();
		this.dispatcher = dispatcher;
		setLayout(null);
		
		sellPrice = new JTextField("");
		sellPrice.setBounds(150, 50, 90, 20);
		add(sellPrice);
		
		sellPriceLabel = new JLabel("Preis");
		sellPriceLabel.setBounds(150, 30, 90, 20);
		add(sellPriceLabel);
		
		sellNumber = new JComboBox<Integer>();
		for(User user : Database.getUsers())
		{
			sellNumber.addItem(user.getVerkaufsnummer());
		}
		sellNumber.setBounds(30, 50, 90, 20);
		add(sellNumber);
		
		sellNumberLabel = new JLabel("Nummer");
		sellNumberLabel.setBounds(30, 30, 90, 20);
		add(sellNumberLabel);
		
		addSell = new JButton("Hinzuf�gen");
		addSell.setBounds(60,  90, 100, 30);
		addSell.addActionListener(this);
		add(addSell);
		
		clearSell = new JButton("Zur�cksetzen");
		clearSell.setBounds(160,  90, 100, 30);
		clearSell.addActionListener(this);
		add(clearSell);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		
		if(button == addSell) {
			int id = (Integer) sellNumber.getSelectedItem();
			User user = Database.getUserByNumber(id);
			if(user != null) {
				float preis;
				try {
					preis = Float.parseFloat(sellPrice.getText());
					AddSellingAction action = new AddSellingAction(user, preis);
					dispatcher.addAction(action);
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Der Verkaufspreis ist keine Zahl");
				}
			}else {
				System.out.println("Benutzer " + id + " nicht gefunden.");
			}
		}else if(button == clearSell) {
			sellPrice.setText("");
			sellNumber.setSelectedIndex(0);
		}
		
		dispatcher.dispatch();
		
	}
}
