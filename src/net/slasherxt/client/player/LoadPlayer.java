package net.slasherxt.client.player;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import net.slasherxt.client.console.Console;

import org.jnbt.IntTag;
import org.jnbt.NBTInputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

public class LoadPlayer {
	public static boolean checkSave() {
		String playerPath = "saves/";
		boolean fileFound = new File(playerPath + "player.dat").exists();
		
		if(fileFound) {
			return true;
		} else {
			return false;
		}
	}

	public static void loadPlayer() {
		Console.outputSystemMessage("Loading Game!");
		
		String save = "saves/player.dat";
		
		NBTInputStream in;
		try {
			in = new NBTInputStream(new FileInputStream(new File(save)));
			ArrayList<Tag> tags = new ArrayList<Tag>();
			
			try {
				Tag tag;
				
				while((tag = in.readTag()) != null) {
					tags.add(tag);
				}
				in.close();
			} catch(Exception e) {}
			
			//PlayerName, Money, Industrial, Commercial, Residential
			Player.playerName = (StringTag) getTag(tags, "PlayerName");
			Player.money = (IntTag) getTag(tags, "Money");
			Player.industrial = (IntTag) getTag(tags, "Industrial");
			Player.commercial = (IntTag) getTag(tags, "Commercial");
			Player.residential = (IntTag) getTag(tags, "Residential");
			
		} catch(Exception e) {
			Console.outputError("Could not load save!");
		}
		
		Player.createPlayer(Player.playerName.getValue());
		
		Console.outputSystemMessage("Loading Complete!");
	}
	
	public static Tag getTag(ArrayList<Tag> tags, String str) {
		for(Tag tag:tags) {
			if(tag.getName().equals(str)) {
				Console.outputSystemMessage("Loading: " + tag.getName());
				return tag;
			}
		}
		return null;
	}
}
