package net.slasherxt.client.states;

import javax.swing.JOptionPane;

import net.slasherxt.client.console.Console;
import net.slasherxt.client.management.Payday;
import net.slasherxt.client.management.Taxes;
import net.slasherxt.client.management.Time;
import net.slasherxt.client.map.World;
import net.slasherxt.client.map.tiles.Database_Tiles;
import net.slasherxt.client.map.tiles.Tile;
import net.slasherxt.client.player.LoadPlayer;
import net.slasherxt.client.player.Player;
import net.slasherxt.client.player.SavePlayer;
import net.slasherxt.client.resources.ImageLoader;

import org.jnbt.IntTag;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public int id;
	public int mX, mY;
	private int width = 800, height = 600;
	public boolean tileSelected = false;
	public boolean validTile = false;
	public Tile selectedTile = Database_Tiles.tiles[0];
	
	public GameState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Time.initTime();
		
		if(LoadPlayer.checkSave()) {
			LoadPlayer.loadPlayer();
		}
		
		if(!Player.playerMade) {
			Player.createPlayer(JOptionPane.showInputDialog(null, "Please Input your Player Name!"));
		}
		
		try {
			SavePlayer.save();
		} catch (Exception e) {
			Console.outputError("Cannot Save!");
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		World.drawMap(g);
		
		g.drawRect(50, 0, 725, 40);
		g.drawString("Name: " + Player.playerName.getValue(), 55, 10);
		
		for(int i=0;i<Player.playerData.length;i++) {
			g.drawString(Player.playerDataStrings[i] + ": " + Player.playerData[i].getValue(), 200 + (145*i), 10);
		}
		
		
		g.drawRect(575, 50, 200, 480);
		
		g.drawRect(50, 540, 365, 50);
		if(Time.doubled == false) {
			g.drawString("Time: " + "  " + Time.y + "       " + Time.mo + "    " + Time.w + "     " + Time.d + "   " + Time.h + "    " + (Time.mi/10), 55, 545);	
			ImageLoader.single.draw(5, 540);
		} else {
			g.drawString("Time: " + "  " + Time.y + "       " + Time.mo + "    " + Time.w + "     " + Time.d + "   " + Time.h + "    " + (Time.mi/10)*6, 55, 545);	
			ImageLoader.doubled.draw(0, 540);
		}
		g.drawString("Time: " + "Yrs, " + "Months, " + "Wks, " + "Days, " + "Hrs, " + "Mins", 55, 560);
		
		g.drawRect(425, 540, 350, 50);
		g.drawString("Taxes: " + Taxes.tax.getValue() + "$", 430, 545);
		g.drawString("Payday: " + Payday.pay.getValue() + "$", 430, 565);
		
		if(tileSelected) {
			Integer[] position = Database_Tiles.tilePosList.get(selectedTile);
			
			g.drawString("Tile: " + (Database_Tiles.tileIDList.get(selectedTile)+1) + "\nX: " + (position[0]+1) + "\nY: " + (position[1]+1), 580, 60);
			g.drawString("Type: " + Database_Tiles.tileTypeList.get(selectedTile), 580, 120);
			
			g.drawRect(580, 140, 190, 35);
			g.drawString("BULLDOZE - 50$", 585, 145);
			
			if(validTile) {
				g.drawRect(580, 175, 190, 35);
				g.drawString("INDUSTRIAL - 100$", 585, 180);
				
				g.drawRect(580, 210, 190, 35);
				g.drawString("COMMERCIAL - 65$", 585, 215);
				
				g.drawRect(580, 245, 190, 35);
				g.drawString("RESIDENTIAL - 40$", 585, 250);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Time.tick();
		Database_Tiles.updateTiles();
		Player.updatePlayer();
		
		Input in = gc.getInput();
		mX = Mouse.getX();
		mY = Mouse.getY();
		
		boolean clicked = false;
		int count = 0;
		
		if(in.isKeyPressed(Input.KEY_SPACE)) {
			Time.doubled = !Time.doubled;
		}
	
		
		// Setup Tile Clicking!
		for(int x=0;x<World.tileCount;x++) {
			for(int y=0;y<World.tileCount;y++) {
				if((mX > 50 + (World.tileSize*x)) && (mX < 50 + World.tileSize + (World.tileSize*x)) && (mY < height - 50 - (World.tileSize*y)) && (mY > height - 50 - World.tileSize - (World.tileSize*y)) && (in.isMouseButtonDown(0)) && (!clicked)) {
					clicked = true;
					selectedTile = Database_Tiles.tiles[count];
					tileSelected = true;
					if(Database_Tiles.tileTypeList.get(selectedTile) != "Forest") {
						validTile = true;
					} else {
						validTile = false;
					}
				}
				
				count++;
			}
		}
		
		if(tileSelected) {			
			if((mX > 580) && (mX < 580 + 190) && (mY < height - 140) && (mY > height - 140 - 35) && (in.isMouseButtonDown(0)) && (!clicked)) {				
				if((Player.money.getValue() >= 50) && (!clicked)) {
					clicked = true;
					Player.money = new IntTag("Money", Player.money.getValue() - 50);
					Database_Tiles.tileTypeList.put(selectedTile, "Field");
					
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Industrial") {
						Player.industrial = new IntTag("Industrial", Player.industrial.getValue() - 1);
					}
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Commercial") {
						Player.commercial = new IntTag("Commercial", Player.commercial.getValue() - 1);
					}
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Residential") {
						Player.residential = new IntTag("Residential", Player.residential.getValue() - 1);
					}
					
					tileSelected = false;
				}
			}
			if((mX > 580) && (mX < 580 + 190) && (mY < height - 175) && (mY > height - 175 - 35) && (in.isMouseButtonDown(0)) && (!clicked)) {				
				if((Player.money.getValue() >= 100) && (!clicked)) {
					clicked = true;
					if(Database_Tiles.tileTypeList.get(selectedTile) != "Industrial") {
						Player.money = new IntTag("Money", Player.money.getValue() - 100);
						Database_Tiles.tileTypeList.put(selectedTile, "Industrial");
						Player.industrial = new IntTag("Industrial", Player.industrial.getValue() + 1);
					}
										
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Commercial") {
						Player.commercial = new IntTag("Commercial", Player.commercial.getValue() - 1);
					}
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Residential") {
						Player.residential = new IntTag("Residential", Player.residential.getValue() - 1);
					}
					
					tileSelected = false;
				}
			}
			if((mX > 580) && (mX < 580 + 190) && (mY < height - 210) && (mY > height - 210 - 35) && (in.isMouseButtonDown(0)) && (!clicked)) {				
				if((Player.money.getValue() >= 65) && (!clicked)) {
					clicked = true;
					if(Database_Tiles.tileTypeList.get(selectedTile) != "Commercial") {
						Player.money = new IntTag("Money", Player.money.getValue() - 65);
						Database_Tiles.tileTypeList.put(selectedTile, "Commercial");
						Player.commercial = new IntTag("Commercial", Player.commercial.getValue() + 1);
					}
										
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Industrial") {
						Player.industrial = new IntTag("Industrial", Player.industrial.getValue() - 1);
					}
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Residential") {
						Player.residential = new IntTag("Residential", Player.residential.getValue() - 1);
					}
					
					tileSelected = false;
				}
			}
			if((mX > 580) && (mX < 580 + 190) && (mY < height - 245) && (mY > height - 245 - 35) && (in.isMouseButtonDown(0)) && (!clicked)) {				
				if((Player.money.getValue() >= 40) && (!clicked)) {
					clicked = true;
					if(Database_Tiles.tileTypeList.get(selectedTile) != "Residential") {
						Player.money = new IntTag("Money", Player.money.getValue() - 40);
						Database_Tiles.tileTypeList.put(selectedTile, "Residential");
						Player.residential = new IntTag("Residential", Player.residential.getValue() + 1);
					}
										
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Industrial") {
						Player.industrial = new IntTag("Industrial", Player.industrial.getValue() - 1);
					}
					if(Database_Tiles.tileTypeList.get(selectedTile) == "Commercial") {
						Player.commercial = new IntTag("Commercial", Player.commercial.getValue() - 1);
					}
					
					tileSelected = false;
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
