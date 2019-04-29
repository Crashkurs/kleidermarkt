package de.mwilhelm.kleidermarkt.Action;

import de.mwilhelm.kleidermarkt.Database.Database;
import Entities.User;

public class DeleteUserAction extends Action {

	private final User user;
	
	public DeleteUserAction(User user) {
		this.user = user;
	}
	
	@Override
	public boolean doAction() {
		if(Database.getUsers().contains(user)) {
			Database.removeUser(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean undoAction() {
		User otherUser = Database.getUserByNumber(user.getVerkaufsnummer());
		if(otherUser != null) {
			Database.removeUser(otherUser);
			Database.addUser(user);
			return true;
		}else {
			Database.addUser(user);
			return true;
		}
	}

	@Override
	public String getErrorDescription() {
		return "Fehler beim L�schen des Verk�ufers";
	}

	@Override
	public String getHistoryDescription() {
		return "L�sche Benutzer mit Nummer " + user.getVerkaufsnummer();
	}

}
