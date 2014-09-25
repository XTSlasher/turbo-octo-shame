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
					tiles[count] = new Tile("Forest", count, x, y, ImageLoader.forest);
					
					tileIDList.put(tiles[count], count);
					tileImageList.put(tiles[count], Tile.getImage(tileIDList.get(count)));
					tilePosList.put(tiles[count], new Integer[]{x, y});
					tileTypeList.put(tiles[count], "Forest");
					
					count++;
				}
			}
			
			System.out.println("Database Initialized");
		}
	}

	public static void updateTiles() {
		for(int i=0;i<tiles.length;i++) {
			if(tiles[i] != null) {
				if(tileTypeList.get(tiles[i]) == "Blank") {
					tileImageList.put(tiles[i], ImageLoader.blank);
				}
				if(tileTypeList.get(tiles[i]) == "Field") {
					tileImageList.put(tiles[i], ImageLoader.field);
				}
				if(tileTypeList.get(tiles[i]) == "Forest") {
					tileImageList.put(tiles[i], ImageLoader.forest);
				}
				if(tileTypeList.get(tiles[i]) == "Industrial") {
					tileImageList.put(tiles[i], ImageLoader.in1);
				}
				if(tileTypeList.get(tiles[i]) == "Commercial") {
					tileImageList.put(tiles[i], ImageLoader.cm1);
				}
				if(tileTypeList.get(tiles[i]) == "Residential") {
					tileImageList.put(tiles[i], ImageLoader.rs1);
				}
				
				Tile.updateImage(tiles[i], tileImageList.get(tiles[i]));
			}
		}
	}
}
