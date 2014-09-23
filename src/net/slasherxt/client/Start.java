package net.slasherxt.client;

import net.slasherxt.client.console.StartConsole;
import net.slasherxt.client.states.GameState;
import net.slasherxt.client.states.LoadState;
import net.slasherxt.client.states.MainState;
import net.slasherxt.client.states.OptionsState;
import net.slasherxt.client.states.SaveState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Start extends StateBasedGame {
	// Client Variables
	public static final String gameName = "CityGame";
	public static Start client;
	
	// States
	public static final int mainMenu = 0;
	public static final int options = 1;
	public static final int save = 2;
	public static final int load = 3;
	public static final int mainGame = 4;
	
	public Start(String name) {
		super(name + " (" + "0.0.1" + ")");
		
		client = this;
		
		this.addState(new MainState(mainMenu));
		this.addState(new OptionsState(options));
		this.addState(new SaveState(save));
		this.addState(new LoadState(load));
		this.addState(new GameState(mainGame));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		new StartConsole();
		
		this.getState(mainMenu).init(gc, this);
		this.getState(options).init(gc, this);
		this.getState(save).init(gc, this);
		this.getState(load).init(gc, this);
		//this.getState(mainGame).init(gc, this);
		
		this.enterState(mainMenu);
	}
	
	public static void main(String[] args) {
		AppGameContainer agc;
		
		try {
			agc = new AppGameContainer(new Start(gameName));
			agc.setDisplayMode(800, 600, false);
			agc.setShowFPS(false);
			agc.start();
		} catch(SlickException se) {}
	}
}
