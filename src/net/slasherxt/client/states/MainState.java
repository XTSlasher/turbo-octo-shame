package net.slasherxt.client.states;

import javax.swing.JOptionPane;

import net.slasherxt.client.map.World;
import net.slasherxt.client.map.tiles.Database_Tiles;
import net.slasherxt.client.map.tiles.Tile;
import net.slasherxt.client.player.Player;
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
	public boolean tileSelected = false;
	public Tile selectedTile = Database_Tiles.tiles[0];
	
	public MainState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		if(!Player.playerMade) {
			Player.createPlayer(JOptionPane.showInputDialog(null, "Please Input your Player Name!"));
		}
		ImageLoader.initImages();
		Database_Tiles.initDatabase();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		World.drawMap(g);
		
		g.drawRect(50, 0, 725, 40);
		g.drawString("Name: " + Player.playerName, 55, 10);
		
		for(int i=0;i<Player.playerData.length;i++) {
			g.drawString(Player.playerDataStrings[i] + ": " + Player.playerData[i], 200 + (145*i), 10);
		}
		
		
		g.drawRect(575, 50, 200, 480);
		
		if(tileSelected) {
			Integer[] position = Database_Tiles.tilePosList.get(selectedTile);
			
			g.drawString("Tile: " + (Database_Tiles.tileIDList.get(selectedTile)+1) + "\nX: " + (position[0]+1) + "\nY: " + (position[1]+1), 580, 60);
			g.drawString("Type: " + Database_Tiles.tileTypeList.get(selectedTile), 580, 120);
			
			g.drawRect(580, 140, 190, 35);
			g.drawString("GRASS", 585, 145);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Database_Tiles.updateTiles();
		Player.updatePlayer();
		
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
					selectedTile = Database_Tiles.tiles[count];
					tileSelected = true;
					//Tile.updateImage(Database_Tiles.tiles[count], ImageLoader.field);
				}
				
				count++;
			}
		}
		
		//g.drawRect(580, 140, 190, 35);
		if(tileSelected) {
			if((mX > 580) && (mX < 580 + 190) && (mY < height - 140) && (mY > height - 140 - 35) && (in.isMouseButtonDown(0)) && (!clicked)) {
				if(Player.money > 100) {
					Player.money -= 100;
					Player.residential++;
					Database_Tiles.tileTypeList.put(selectedTile, "Field");
				}
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
