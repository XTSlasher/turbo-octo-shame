package net.slasherxt.client.files;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import net.slasherxt.client.console.Console;
import net.slasherxt.client.management.Payday;
import net.slasherxt.client.management.Taxes;
import net.slasherxt.client.management.Time;
import net.slasherxt.client.player.Player;

import org.jnbt.IntTag;
import org.jnbt.NBTInputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

public class Load {
	public static boolean checkSave() {
		String playerPath = "saves/";
		boolean fileFound = new File(playerPath + "player.dat").exists();
		
		if(fileFound) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void loadManagement() {
		Console.outputSystemMessage("Loading Management!");
		
		String save = "saves/management.dat";
		
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
			
			//Load Tags here
			
			//Time
			// Time.(Years, Months, Weeks, Days, Hours, Minutes)
			Time.years = (IntTag) getTag(tags, "Time.years");
			Time.months = (IntTag) getTag(tags, "Time.months");
			Time.weeks = (IntTag) getTag(tags, "Time.weeks");
			Time.days = (IntTag) getTag(tags, "Time.days");
			Time.hours = (IntTag) getTag(tags, "Time.hours");
			Time.minutes = (IntTag) getTag(tags, "Time.minutes");			
			
			//Payday
			// Payday
			Payday.pay = (IntTag) getTag(tags, "Payday");
			
			//Taxes
			// Taxes
			Taxes.tax = (IntTag) getTag(tags, "Taxes");
			
		} catch(Exception e) {
			Console.outputError("Could not load save!");
		}
		
		Console.outputSystemMessage("Loading Complete!");
	}

	public static void loadPlayer() {
		Console.outputSystemMessage("Loading Player!");
		
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
