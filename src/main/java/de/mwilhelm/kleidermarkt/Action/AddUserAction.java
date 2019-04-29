package de.mwilhelm.kleidermarkt.Action;

import de.mwilhelm.kleidermarkt.Database.Database;
import Entities.User;

public class AddUserAction extends Action {
	
	private User user;
	
	public AddUserAction(User user)
	{
		this.user = user;
	}

	@Override
	public boolean doAction() {
		User temp = Database.getUserByNumber(user.getVerkaufsnummer());
		if(temp != null)
			return false;
		Database.addUser(user);
		return true;
	}

	@Override
	public boolean undoAction() {
		User temp = Database.getUserByNumber(user.getVerkaufsnummer());
		if(temp == null)
			return false;
		Database.removeUser(user);
		return true;
	}

	@Override
	public String getErrorDescription() {
		return "Error while adding a user or undoing it";
	}

	@Override
	public String getHistoryDescription() {
		return "Erstelle Verkäufer mit Nummer " + user.getVerkaufsnummer() + " und Namen " + user.getVerkufer();
	}

}
