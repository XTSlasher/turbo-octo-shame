package net.slasherxt.client.management;

import net.slasherxt.client.console.Console;
import net.slasherxt.client.player.Player;

import org.jnbt.IntTag;

public class Payday {
	public static IntTag pay = new IntTag("Payday", 0);
	
	public static void addPay() {
		if(Time.d == 4 && Time.h == 16 && Time.mi == 1) {
			Console.outputMessage("Payday", "MoneyManager");
			Player.money = new IntTag("Money", Player.money.getValue() + pay.getValue());
		}
		if(Time.d == 1 && Time.h == 16 && Time.mi == 1) {
			Console.outputMessage("Payday", "MoneyManager");
			Player.money = new IntTag("Money", Player.money.getValue() + pay.getValue());
		}
	}
	
	public static void updatePayment() {
		int it = Player.industrial.getValue();
		int cm = Player.commercial.getValue();
		int rs = Player.residential.getValue();
		
		int tp = (it*64) + (cm*50) + (rs*30);
		
		pay = new IntTag("Payday",tp);
		
		addPay();
	}
}
