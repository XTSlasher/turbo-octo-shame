package net.slasherxt.client.map;

import net.slasherxt.client.map.tiles.Database_Tiles;

import org.newdawn.slick.Graphics;

public class World {
	public static int tileSize = 32;
	public static int tileCount = 15;
	public static int count = 0;
	
	public static void drawMap(Graphics g) {
		g.drawRect(50, 50, tileSize * tileCount, tileSize * tileCount);
		
		for(int x=0;x<tileCount;x++) {
			for(int y=0;y<tileCount;y++) {
				g.drawRect(50 + (tileSize*x), 50 + (tileSize*y), tileSize, tileSize);
				g.drawImage(Database_Tiles.tileImageList.get(Database_Tiles.tiles[count]), 50 + (tileSize*x), 50 + (tileSize*y));
								
				count++;
			}
		}
		
		count = 0;
	}
}
