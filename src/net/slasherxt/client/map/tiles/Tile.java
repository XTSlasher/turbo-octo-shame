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
	
	public static void updateImage(Image newImage) {
		image = newImage;
		
		getImage();
	}

	public void updateType(String string) {
		type = string;
		
		if(string == "Blank") {
			updateImage(ImageLoader.blank);
		}
		if(string == "Field") {
			updateImage(ImageLoader.field);
		}
		if(string == "Forest") {
			updateImage(ImageLoader.forest);
		}
	}
}
