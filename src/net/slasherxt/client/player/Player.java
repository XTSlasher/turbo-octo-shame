package net.slasherxt.client.player;

import java.util.HashMap;

public class Player {
	public static boolean playerMade = false;
	
	public static String playerName;
	public static int money, industrial, commercial, residential;
	
	public static String[] playerDataStrings = {"money", "industrial", "commercial", "residential"};
	public static Integer[] playerData = new Integer[playerDataStrings.length];
	public static HashMap<String, Integer> playerStorage = new HashMap<String, Integer>();
	
	public static void createPlayer(String name) {
		playerName = name;
		money = 150;
		industrial = 0;
		commercial = 0;
		residential = 0;
		
		playerMade = true;
		
		createStorage();
	}

	private static void createStorage() {
		playerData[0] = money;
		playerData[1] = industrial;
		playerData[2] = commercial;
		playerData[3] = residential;
		
		for(int i=0;i<playerData.length;i++) {
			if(playerData[i] != null) {
				playerStorage.put(playerDataStrings[i], playerData[i]);
			}
		}
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
