package net.slasherxt.client.map.tiles;

import java.util.HashMap;

import net.slasherxt.client.map.World;
import net.slasherxt.client.resources.ImageLoader;

import org.newdawn.slick.Image;

public class Database_Tiles{
	
	public static Tile[] tiles = new Tile[1000];
	public static HashMap<Tile, Integer> tileIDList = new HashMap<Tile, Integer>();
	public static HashMap<Tile, Image> tileImageList = new HashMap<Tile, Image>();
	public static HashMap<Tile, Integer[]> tilePosList = new HashMap<Tile, Integer[]>();
	public static HashMap<Tile, String> tileTypeList = new HashMap<Tile, String>();
	
	public static int count = 0;
	public static boolean loaded = false;
	
	public static void initDatabase() {
		if(!loaded) {
			loaded = true;
			System.out.println("Initializing Database");
			
			for(int x=0;x<World.tileCount;x++){
				for(int y=0;y<World.tileCount;y++) {
					tiles[count] = new Tile("Blank", count, x, y, ImageLoader.blank);
					
					tileIDList.put(tiles[count], count);
					tileImageList.put(tiles[count], Tile.getImage(tileIDList.get(count)));
					tilePosList.put(tiles[count], new Integer[]{x, y});
					tileTypeList.put(tiles[count], "Blank");
					
					count++;
				}
			}
			
			
			System.out.println("Database Initialized");
		}
	}
}
