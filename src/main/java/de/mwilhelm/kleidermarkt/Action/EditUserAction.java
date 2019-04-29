package de.mwilhelm.kleidermarkt.Action;

import de.mwilhelm.kleidermarkt.Database.Database;
import Entities.User;

public class EditUserAction extends Action {
	
	private User user;
	
	private User oldUser;
	
	public EditUserAction(User user)
	{
		this.user = user;
	}
	
	@Override
	public boolean doAction() {
		oldUser = Database.getUserByNumber(user.getVerkaufsnummer());
		if(oldUser == null)
		{
			return false;
		}
		Database.removeUser(oldUser);
		Database.addUser(user);
		return true;
	}

	@Override
	public boolean undoAction() {
		User temp = Database.getUserByNumber(user.getVerkaufsnummer());
		if(temp == null)
		{
			return false;
		}
		temp = Database.getUserByNumber(oldUser.getVerkaufsnummer());
		if(temp != null)
		{
			return false;
		}
		Database.removeUser(user);
		Database.addUser(oldUser);
		return true;
	}

	@Override
	public String getErrorDescription() {
		return "Error while editing user or undoing it";
	}

	@Override
	public String getHistoryDescription() {
		String nummer = user.getVerkaufsnummer() == oldUser.getVerkaufsnummer() ? "" : "Nummer: " + user.getVerkaufsnummer() + ",";
		String name = user.getVerkufer().equals(oldUser.getVerkufer()) ? "" : "Name: " + user.getVerkufer() + ",";
		String prozent = user.getAbgabe() == oldUser.getAbgabe() ? "" : "ProzentAbgabe: " + user.getAbgabe();
		return "Editiere Verk�ufer " + user.getVerkaufsnummer() + "[" + nummer + name + prozent + "]";
	}

}
