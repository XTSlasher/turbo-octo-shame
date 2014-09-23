package net.slasherxt.client.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
	public static boolean loaded = false;
	
	// Misc
	public static Image title;
	
	// Buttons
	public static Image button;
	public static Image overlay;
	
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
			
			title = new Image("res/title.png");
			
			button = new Image("res/buttons/buttonOutline.png");
			overlay = new Image("res/buttons/buttonOverlay.png");
			
			blank = new Image("res/tiles/blank.png");
			field = new Image("res/tiles/grass.png");
			forest = new Image("res/tiles/forest.png");
			
			System.out.println("Images Loaded");
		}	
	}
}
