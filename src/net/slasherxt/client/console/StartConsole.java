package net.slasherxt.client.console;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class StartConsole extends JFrame{
	public StartConsole() {
		new JFrame();
		
		setSize(400, 250);
		setTitle("Console");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocation(100, 100);
		
		add(new Console(this));
	}
}
