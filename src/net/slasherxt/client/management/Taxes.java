package net.slasherxt.client.management;

import net.slasherxt.client.console.Console;
import net.slasherxt.client.player.Player;

import org.jnbt.IntTag;

public class Taxes {
	public static IntTag tax = new IntTag("Taxes", 0);
	
	public static void deductTaxes() {
		if(Time.d == 3 && Time.h == 12 && Time.mi == 1) {
			Console.outputMessage("Deducting Taxes", "MoneyManager");
			Player.money = new IntTag("Money", Player.money.getValue() - tax.getValue());
		}
	}
	
	public static void updateTaxes() {
		int it = Player.industrial.getValue();
		int cm = Player.commercial.getValue();
		int rs = Player.residential.getValue();
		
		int tt = ((it*60)/2) + ((cm*52)/2) + ((rs*34)/2);
		
		tax = new IntTag("Taxes", tt);
		
		deductTaxes();
	}
}
