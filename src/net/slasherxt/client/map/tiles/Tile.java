package net.slasherxt.client.map.tiles;

import net.slasherxt.client.resources.ImageLoader;

import org.newdawn.slick.Image;

public class Tile {
	public static int id, xPos, yPos;
	public static String type;
	public static Image image;
	
	public Tile(String type, int id, int x, int y, Image i) {
		Tile.type = type;
		Tile.id = id;
		xPos = x;
		yPos = y;
		image = i;
	}
	
	public static int getID() {
		return id;
	}
	
	public static int getX() {
		return xPos;
	}
	
	public static int getY() {
		return yPos;
	}
	
	public static String getType() {
		return type;
	}
	
	public static Image getImage() {
		return image;
	}
	
	public static Image getImage(Integer id) {
		return getImage();
	}
	
	public static void updateImage(Tile t, Image newImage) {
		image = newImage;
		
		Database_Tiles.tileImageList.put(t, newImage);
		
		getImage();
	}

	public void updateType(int id, String string) {
		type = string;
		
		if(string == "Blank") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.blank);
		}
		if(string == "Field") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.field);
		}
		if(string == "Forest") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.forest);
		}
		if(string == "Industrial") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.in1);
		}
		if(string == "Commercial") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.cm1);
		}
		if(string == "Residential") {
			updateImage(Database_Tiles.tiles[id], ImageLoader.rs1);
		}
	}
}
