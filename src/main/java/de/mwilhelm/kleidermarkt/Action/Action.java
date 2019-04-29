package de.mwilhelm.kleidermarkt.Action;

import java.io.Serializable;

public abstract class Action implements Serializable {
	
	public abstract boolean doAction();
	public abstract boolean undoAction();
	
	public abstract String getErrorDescription();
	
	public abstract String getHistoryDescription();
}
