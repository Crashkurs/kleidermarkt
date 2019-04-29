package Windows.History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JTextArea;

import de.mwilhelm.kleidermarkt.Action.Action;
import de.mwilhelm.kleidermarkt.Action.ActionDispatcher;
import Windows.BaseWindow;

public class HistoryWindow extends BaseWindow implements ActionListener {
	
	private final ActionDispatcher dispatcher;
	
	private JTextArea area;
	
	private JButton undoLastAction;

	public HistoryWindow(ActionDispatcher dispatcher)
	{
		this.dispatcher = dispatcher;
		undoLastAction = new JButton("Letzte Aktion r�ckg�ngig machen");
		undoLastAction.setBounds(50, 50, 150, 30);
		undoLastAction.addActionListener(this);
		add(undoLastAction);
		updateArea(dispatcher.getHistory());
	}
	
	private void updateArea(Queue<Action> queue)
	{
		if(area == null)
		{
			area = new JTextArea(30, 60);
			area.setBounds(50, 140, 400, 400);
			add(area);
		}
		
		area.setText("");
		
		for(Action action : queue)
		{
			area.append(action.getHistoryDescription() + "\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispatcher.undoLastAction();
		updateArea(dispatcher.getHistory());
	}
}
