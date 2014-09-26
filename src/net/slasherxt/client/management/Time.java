package net.slasherxt.client.management;

import org.jnbt.IntTag;

public class Time {
	public static boolean doubled = false;
	
	public static int y, mo, w, d, h, mi;
	
	public static IntTag years;
	public static IntTag months;
	public static IntTag weeks;
	public static IntTag days;
	public static IntTag hours;
	public static IntTag minutes;
	
	public static void initTime() {
		y = 1;
		mo = 1;
		w = 1;
		d = 1;
		h = 1;
		mi = 1;
		
		updateTime();
	}
	
	private static void updateTime() {
		years = new IntTag("Time.Years", y);
		months = new IntTag("Time.Months", mo);
		weeks = new IntTag("Time.Weeks", w);
		days = new IntTag("Time.Days", d);
		hours = new IntTag("Time.Hours", h);
		minutes = new IntTag("Time.Minutes", mi);
	}
	
	public static void tick() {
		mi++;
		
		if(!doubled) {
			if(mi >= 600) {
				mi = 1;
				h++;
				if(h >= 24) {
					h = 1;
					d++;
					if(d >= 7) {
						d = 1;
						w++;
						if(w >= 4) {
							w = 1;
							mo++;
							if(mo >= 12) {
								mo = 1;
								y++;
							}
						}
					}
				}
			}
		} else {
			if(mi >= 100) {
				mi = 1;
				h++;
				if(h >= 24) {
					h = 1;
					d++;
					if(d >= 7) {
						d = 1;
						w++;
						if(w >= 4) {
							w = 1;
							mo++;
							if(mo >= 12) {
								mo = 1;
								y++;
							}
						}
					}
				}
			}
		}
		
		
		Payday.updatePayment();
		Taxes.updateTaxes();
		updateTime();
	}
}
