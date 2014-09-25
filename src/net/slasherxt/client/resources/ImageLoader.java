package net.slasherxt.client.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
	public static boolean loaded = false;
	
	// Misc
	public static Image title;
	public static Image single;
	public static Image doubled;
	
	// Buttons
	public static Image button;
	public static Image overlay;
	
	// Tiles
	public static Image blank;
	public static Image field;
	public static Image forest;
	
	// Buildings
	/** Tier 1 */
	public static Image in1;
	public static Image cm1;
	public static Image rs1;
	
	// Items
	
	
	public static void initImages() throws SlickException {
		if(!loaded) {
			loaded = true;
			
			System.out.println("Loading Images");
			
			title = new Image("res/title.png");
			single = new Image("res/buttons/single.png");
			doubled = new Image("res/buttons/double.png");
			
			button = new Image("res/buttons/buttonOutline.png");
			overlay = new Image("res/buttons/buttonOverlay.png");
			
			blank = new Image("res/tiles/blank.png");
			field = new Image("res/tiles/grass.png");
			forest = new Image("res/tiles/forest.png");
			
			in1 = new Image("res/tiles/buildings/industrial/industrial_t1.png");
			cm1 = new Image("res/tiles/buildings/commercial/commercial_t1.png");
			rs1 = new Image("res/tiles/buildings/residential/residential_t1.png");
			
			System.out.println("Images Loaded");
		}	
	}
}
