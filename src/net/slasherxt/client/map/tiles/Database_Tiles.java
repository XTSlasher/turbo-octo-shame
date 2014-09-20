package net.slasherxt.client.map.tiles;

import net.slasherxt.client.map.World;
import net.slasherxt.client.resources.ImageLoader;

public class Database_Tiles{
	public static Tile[] tiles = new Tile[225];
	public static int count = 0;
	public static boolean loaded = false;
	
	public static void initDatabase() {
		if(!loaded) {
			loaded = true;
			System.out.println("Initializing Database");
			
			for(int x=0;x<World.tileCount;x++) {
				for(int y=0;y<World.tileCount;y++) {
					System.out.println(count);
					
					tiles[count] = new Tile("Blank", count, x, y, ImageLoader.blank);
					count++;
				}
			}
			
			System.out.println("Database Initialized");
		}
		
		getImage();
	}
	
	public static void getImage() {
		for(int i=0;i<tiles.length;i++) {
			System.out.println(i + ") " + tiles[i].getImage());
		}
	}
}
