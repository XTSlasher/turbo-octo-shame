package net.slasherxt.client.player;

import java.util.HashMap;

import net.slasherxt.client.console.Console;

import org.jnbt.IntTag;
import org.jnbt.StringTag;

public class Player {
	public static boolean playerMade = false;
	
	public static StringTag playerName = new StringTag("PlayerName", null);
	public static IntTag money = new IntTag("Money", 0);
	public static IntTag industrial = new IntTag("Industrial", 0);
	public static IntTag commercial = new IntTag("Commercial", 0);
	public static IntTag residential = new IntTag("Residential", 0);
	
	public static String[] playerDataStrings = {"money", "industrial", "commercial", "residential"};
	public static IntTag[] playerData = new IntTag[playerDataStrings.length];
	public static HashMap<String, IntTag> playerStorage = new HashMap<String, IntTag>();
	
	public static void createPlayer(String name) {
		Console.outputMessage("Creating Player");
		
		playerName = new StringTag("PlayerName", name);
		money = new IntTag("Money", 150);
		industrial = new IntTag("Industrial", 0);
		commercial = new IntTag("Commercial", 0);
		residential = new IntTag("Residential", 0);
		
		playerMade = true;
		
		Console.outputMessage("Player Created");
		
		createStorage();
	}

	private static void createStorage() {
		Console.outputMessage("Creating Player Data Storage");
		
		playerData[0] = money;
		playerData[1] = industrial;
		playerData[2] = commercial;
		playerData[3] = residential;
		
		for(int i=0;i<playerData.length;i++) {
			if(playerData[i] != null) {
				playerStorage.put(playerDataStrings[i], playerData[i]);
			}
		}
		

		Console.outputMessage("Player Data Storage Created");
	}
	
	public static void updatePlayer() {		
		updateMoney();
		updateProperties();
	}
	
	private static void updateMoney() {
		playerData[0] = money;
	}
	
	private static void updateProperties() {
		playerData[1] = industrial;
		playerData[2] = commercial;
		playerData[3] = residential;
	}
}
