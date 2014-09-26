package net.slasherxt.client.files;

import java.io.File;
import java.io.FileOutputStream;

import net.slasherxt.client.console.Console;
import net.slasherxt.client.management.Payday;
import net.slasherxt.client.management.Taxes;
import net.slasherxt.client.management.Time;
import net.slasherxt.client.player.Player;

import org.jnbt.IntTag;
import org.jnbt.NBTOutputStream;

public class Save {
	public static void savePlayer() throws Exception {
		Console.outputSystemMessage("Saving Player.");
		
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
	
	public static void saveManagement() throws Exception {
		Console.outputSystemMessage("Saving Management.");
		
		String path = "saves/";
		String fileName = "management.dat";
		
		if(!new File(path).exists()) new File(path).mkdirs();
		
		NBTOutputStream out  = new NBTOutputStream(new FileOutputStream(new File(path + fileName)));

		out.writeTag(Time.years);
		out.writeTag(Time.months);
		out.writeTag(Time.weeks);
		out.writeTag(Time.days);
		out.writeTag(Time.hours);
		out.writeTag(Time.minutes);
		out.writeTag(Payday.pay);
		out.writeTag(Taxes.tax);
		
		out.close();
		
		Console.outputSystemMessage("Saving Complete!");
	}
}
