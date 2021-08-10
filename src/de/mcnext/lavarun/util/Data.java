package de.mcnext.lavarun.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

public class Data {
	
	private final String PREFIX = "§6LavaRun §7» ",
						 NO_PERMS = PREFIX + "§cDazu hast du keine Rechte!",
						 NO_PLAYER = PREFIX + "§cDu musst ein Spieler sein!";
	
	private final String LOCATION_UTIL_NAME = "§6§lLocation§7-§c§lUtil";
	
	private HashMap<String, Location> tempPos1 = new HashMap<>();
	private HashMap<String, Location> tempPos2 = new HashMap<>();
	private ArrayList<String> inBuildMode = new ArrayList<>();
	
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public String Prefix() {
		return PREFIX;
	}
	
	public String Error_No_Perms() {
		return NO_PERMS;
	}
	
	public String Error_No_Player() {
		return NO_PLAYER;
	}
	
	public String Name_Location_Util() {
		return LOCATION_UTIL_NAME;
	}
	
	public HashMap<String, Location> getTempPos1() {
		return tempPos1;
	}
	
	public HashMap<String, Location> getTempPos2() {
		return tempPos2;
	}
	
	public ArrayList<String> getInBuildMode() {
		return inBuildMode;
	}
}
