package net.slasherxt.client.console;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Console extends JPanel implements Runnable {
	
	private static String[] messageLog = new String[10];
	private static String[] messageLog_Old = {};
	
	Thread thread = new Thread(this);
	StartConsole frame;
	
	public Console(StartConsole f) {
		frame = f;
		
		startConsole();
	}
	
	public void startConsole() {
		System.out.println("Starting Console!");
		setName("Console");
		
		thread.start();
		
		System.out.println("Console Started");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, 400, 250);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 250);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20F));
		for(int i=0;i<messageLog.length;i++) {
			if(messageLog[i] == null) {
				g.drawString("", 5, 30 + (20*i));
			} else {
				g.drawString(messageLog[i], 5, 30 + (20*i));
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			repaint();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				outputError(e.toString());
			}
		}
	}
	
	public static void outputMessage(String message, String sender) {
		System.out.println(message);
		updateLog("["+sender+"] " + message);
	}
	
	public static void outputError(String error) {
		System.err.println(error);
		updateLog("[ERROR]" + error);
	}
	
	public static void outputSystemMessage(String message) {
		System.out.println(message);
		updateLog("[SYSOUT]" + message);
	}
	
	public static void updateLog(String newLog) {
		messageLog_Old = messageLog;
		for(int i=1;i<messageLog_Old.length + 1;i++) {
			int dif = messageLog_Old.length-i;
			if(dif!=0) {
				messageLog[i-1] = messageLog_Old[i];
			} else {
				messageLog[9] = newLog;
			}
		}
	}
}
