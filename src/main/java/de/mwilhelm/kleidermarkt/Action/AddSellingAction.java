package de.mwilhelm.kleidermarkt.Action;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.User;

public class AddSellingAction extends Action {

	private final User user;
	
	private final float price;
	
	 public AddSellingAction(User user, float price) {
		this.user = user;
		this.price = price;
	}
	
	@Override
	public boolean doAction() {
		if(user == null)
			return false;
		if(price < 0)
			return false;
		user.addVerkauf(price);
		JOptionPane.showMessageDialog(new JFrame(), "Verkauf f�r Nummer " + user.getVerkaufsnummer() + " und Preis " + price + "� wurde hinzugef�gt");
		return true;
	}

	@Override
	public boolean undoAction() {
		if(user == null)
			return false;
		if(price < 0)
			return false;
		if(user.getVerkaufsListe().contains(price))
		{
			user.getVerkaufsListe().remove(price);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String getErrorDescription() {
		return "Fehler beim Hinzuf�gen/R�ckg�ngig machen eines Verkaufs";
	}

	@Override
	public String getHistoryDescription() {
		return "F�ge Verkauf von " + price + "� dem Verk�ufer " + user.getVerkaufsnummer() + " hinzu.";
	}

}
