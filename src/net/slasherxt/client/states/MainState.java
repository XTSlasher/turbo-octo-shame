package net.slasherxt.client.states;

import net.slasherxt.client.map.World;
import net.slasherxt.client.map.tiles.Database_Tiles;
import net.slasherxt.client.map.tiles.Tile;
import net.slasherxt.client.resources.ImageLoader;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends BasicGameState {

	public int id;
	public int mX, mY;
	private int width = 800, height = 600;
	
	public MainState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		ImageLoader.initImages();
		Database_Tiles.initDatabase();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		World.drawMap(g);
		
		g.drawString("MX: " + mX + "\nMY: " + mY, 10, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input in = gc.getInput();
		mX = Mouse.getX();
		mY = Mouse.getY();
		
		boolean clicked = false;
		int count = 0;
	
		
		// Setup Tile Clicking!
		for(int x=0;x<World.tileCount;x++) {
			for(int y=0;y<World.tileCount;y++) {
				if((mX > 50 + (World.tileSize*x)) && (mX < 50 + World.tileSize + (World.tileSize*x)) && (mY < height - 50 - (World.tileSize*y)) && (mY > height - 50 - World.tileSize - (World.tileSize*y)) && (in.isMouseButtonDown(0)) && (!clicked)) {
					clicked = true;
					System.out.println("Clicked Tile[" + count + "]");
					// TODO Open Tile Options!
					//Tile.updateImage(Database_Tiles.tiles[count], ImageLoader.field);
				}
				if((mX > 50 + (World.tileSize*x)) && (mX < 50 + World.tileSize + (World.tileSize*x)) && (mY < height - 50 - (World.tileSize*y)) && (mY > height - 50 - World.tileSize - (World.tileSize*y)) && (in.isMouseButtonDown(1)) && (!clicked)) {
					clicked = true;
					System.out.println("Clicked Tile[" + count + "]");
					// TODO Open Tile Options!
					//Tile.updateImage(Database_Tiles.tiles[count], ImageLoader.forest);
				}
				
				count++;
			}
		}
		
		
		
		in.clearMousePressedRecord();
		in.clearKeyPressedRecord();
		clicked = false;
		count = 0;
	}

	@Override
	public int getID() {
		return id;
	}
}
