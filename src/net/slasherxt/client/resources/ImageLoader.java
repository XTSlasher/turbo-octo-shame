package net.slasherxt.client.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
	public static boolean loaded = false;
	
	// Tiles
	public static Image blank;
	public static Image field;
	public static Image forest;
	
	// Buildings
	
	// Items
	
	
	public static void initImages() throws SlickException {
		if(!loaded) {
			loaded = true;
			
			System.out.println("Loading Images");
			
			blank = new Image("res/tiles/blank.png");
			field = new Image("res/tiles/grass.png");
			forest = new Image("res/tiles/forest.png");
			
			System.out.println("Images Loaded");
		}	
	}
}
