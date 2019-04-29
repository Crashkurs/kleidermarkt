package de.mwilhelm.kleidermarkt.Action;

import java.util.LinkedList;
import java.util.Queue;

public class ActionDispatcher {
	
	private Queue<Action> queue = new LinkedList<Action>();
	
	private Queue<Action> history = new LinkedList<Action>();
	
	public ActionDispatcher()
	{
		
	}

	public void addAction(Action action)
	{
		queue.add(action);
	}
	
	public void undoLastAction()
	{
		Action action = history.poll();
		boolean success = action.undoAction();
		if(success) {
			
		}else {
			System.out.println(action.getErrorDescription());
			history.add(action);
		}
	}
	
	public Queue<Action> getHistory() 
	{
		return history;
	}
	
	public void dispatch()
	{
		while(!queue.isEmpty())
		{
			Action action = queue.poll();
			boolean success = action.doAction();
			if(!success)
			{
				System.out.println(action.getErrorDescription());
			}else{
				history.add(action);
			}
		}
	}
}
