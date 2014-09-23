package net.slasherxt.client.states;

import java.awt.Font;

import net.slasherxt.client.Start;
import net.slasherxt.client.console.Console;
import net.slasherxt.client.map.tiles.Database_Tiles;
import net.slasherxt.client.resources.ImageLoader;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends BasicGameState {

	public int id;
	public String button;
	public boolean highlight = false;
	
	Rectangle play = new Rectangle(100, 350, 200, 100);
	
	Font font;
	TrueTypeFont ttf;
	
	public MainState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		ImageLoader.initImages();
		Database_Tiles.initDatabase();
		
		font = new Font("Veranda", Font.BOLD, 20);
		ttf = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		ImageLoader.title.draw(100, 50, 2);
		
		ImageLoader.button.draw(100, 350, 2);
		
		font = new Font("Veranda", Font.BOLD, 50);
		ttf = new TrueTypeFont(font, true);
		
		ttf.drawString(130, 365, "PLAY");
		
		if((button == "Play") && (highlight)) {
			ImageLoader.overlay.draw(100, 350, 2);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input in = gc.getInput();
		int xP = Mouse.getX();
		int yP = Mouse.getY();
		
		boolean clicked = false;
		
		if((xP < 300) && (xP > 100) && (yP > 150) && (yP < 250)) {
			highlight = true;
			button = "Play";
		} else {
			highlight = false;
			button = "";
		}
		if((xP < 300) && (xP > 100) && (yP > 150) && (yP < 250) && (in.isMouseButtonDown(0)) && (clicked ==  false)) {
			Console.outputError("Entering State of: " + Start.mainGame);
			clicked = true;
			Start.client.getState(Start.mainGame).init(gc, sbg);
			
			sbg.enterState(Start.mainGame);
		}
		
		clicked = false;
		in.clearControlPressedRecord();
		in.clearKeyPressedRecord();
		in.clearMousePressedRecord();
	}

	@Override
	public int getID() {
		return id;
	}

}
