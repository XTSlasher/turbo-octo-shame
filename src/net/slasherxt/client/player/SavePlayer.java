package net.slasherxt.client.player;

import java.io.File;
import java.io.FileOutputStream;

import net.slasherxt.client.console.Console;

import org.jnbt.NBTOutputStream;

public class SavePlayer {
	public static void save() throws Exception {
		Console.outputSystemMessage("Saving...");
		
		String path = "saves/";
		String fileName = "player.dat";
		
		if(!new File(path).exists()) new File(path).mkdirs();
		
		NBTOutputStream out  = new NBTOutputStream(new FileOutputStream(new File(path + fileName)));
		out.writeTag(Player.playerName);
		for(int i=0;i<Player.playerData.length;i++) {
			if(Player.playerData[i] != null) {
				out.writeTag(Player.playerData[i]);
			}
		}
		out.close();
		
		Console.outputSystemMessage("Saving Complete!");
	}
}
