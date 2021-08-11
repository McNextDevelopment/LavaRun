package de.mcnext.lavarun.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import de.mcnext.lavarun.LavaRun;

public class LocationManager {
	
	private FileManager locationManager;
	private FileManager regionManager;
	private LavaRun instance;
	private Cuboid arena;
	
	
	public LocationManager(LavaRun instance) {
		this.instance = instance;
		locationManager = new FileManager(instance, "locations");
		regionManager = new FileManager(instance, "regions");
	}
	
	public void setLocation(Location loc, String name) {
		try {
			locationManager.setValue(name + ".World", loc.getWorld().getName());
			locationManager.setValue(name + ".X", loc.getX());
			locationManager.setValue(name + ".Y", loc.getY());
			locationManager.setValue(name + ".Z", loc.getZ());
			locationManager.setValue(name + ".Yaw", loc.getYaw());
			locationManager.setValue(name + ".Pitch", loc.getPitch());
		} catch (Exception e) {
			instance.getLogger().warning(instance.getData().Prefix() + "Error trying to set Location: " + name);
			e.printStackTrace();
		}
	}
	
	public Location getLocation(String name) {
		try {
			World world = Bukkit.getWorld(locationManager.getString(name + ".World"));
			double x = locationManager.getDouble(name + ".X");
			double y = locationManager.getDouble(name + ".Y");
			double z = locationManager.getDouble(name + ".Z");
			float yaw = locationManager.getFloat(name + ".Yaw");
			float pitch = locationManager.getFloat(name + ".Pitch");
			
			return new Location(world, x, y, z, yaw, pitch);
		} catch (Exception e) {
			instance.getLogger().warning(instance.getData().Prefix() + "Error trying to get Location: " + name);
			e.printStackTrace();
		}
		return null;
	}
	
	public void setArena(Location pos1, Location pos2) {
		try {
			regionManager.setValue("Pos1.World", pos1.getWorld().getName());
			regionManager.setValue("Pos1.X", pos1.getX());
			regionManager.setValue("Pos1.Y", pos1.getY());
			regionManager.setValue("Pos1.Z", pos1.getZ());
			
			regionManager.setValue("Pos2.World", pos2.getWorld().getName());
			regionManager.setValue("Pos2.X", pos2.getX());
			regionManager.setValue("Pos2.Y", pos2.getY());
			regionManager.setValue("Pos2.Z", pos2.getZ());
			
			setArena(new Cuboid(pos1, pos2));
		} catch (Exception e) {
			instance.getLogger().warning(instance.getData().Prefix() + "Error trying to set the Arena");
			e.printStackTrace();
		}
	}
	
	public void loadArena() {
		try {
			World world1 = Bukkit.getWorld(regionManager.getString("Pos1.World"));
			double x1 = regionManager.getDouble("Pos1.X");
			double y1 = regionManager.getDouble("Pos1.Y");
			double z1 = regionManager.getDouble("Pos1.Z");
			
			World world2 = Bukkit.getWorld(regionManager.getString("Pos2.World"));
			double x2 = regionManager.getDouble("Pos2.X");
			double y2 = regionManager.getDouble("Pos2.Y");
			double z2 = regionManager.getDouble("Pos2.Z");
			
			Location loc1 = new Location(world1, x1, y1, z1),
					 loc2 = new Location(world2, x2, y2, z2);
			
			setArena(new Cuboid(loc1, loc2));
		} catch (Exception e) {
			instance.getLogger().warning(instance.getData().Prefix() + "Error trying to load the Arena");
			e.printStackTrace();
			setArena(null);
		}
	}

	public Cuboid getArena() {
		return arena;
	}

	public void setArena(Cuboid arena) {
		this.arena = arena;
	}
}
