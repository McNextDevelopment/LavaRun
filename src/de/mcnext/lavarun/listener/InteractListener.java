package de.mcnext.lavarun.listener;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import de.mcnext.lavarun.LavaRun;

public class InteractListener implements Listener {
	
	private LavaRun instance;
	private HashMap<String, Location> tempPos1;
	private HashMap<String, Location> tempPos2;
	
	public InteractListener(LavaRun instance) {
		this.instance = instance;
		this.tempPos1 = instance.getData().getTempPos1();
		this.tempPos2 = instance.getData().getTempPos2();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getInventory().getItemInMainHand() == null) return;
		if(p.getInventory().getItemInMainHand().getItemMeta() == null) return;
		if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) return;
		
		if(p.getInventory().getItemInMainHand().getType().equals(Material.IRON_NUGGET) && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(instance.getData().Name_Location_Util())) {
			e.setCancelled(true);
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getHand().equals(EquipmentSlot.HAND)) {
					tempPos1.put(p.getName(), e.getClickedBlock().getLocation());
					if(tempPos2.containsKey(p.getName())) {
						p.sendMessage("§aBeide Positionen gesetzt, erstelle nun die Arena mit /LavaRun Create.");
					} else
						p.sendMessage(instance.getData().Prefix() + "§aPosition 1 §bgesetzt.");
				}
			} else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
				if(e.getHand().equals(EquipmentSlot.HAND)) {
					tempPos2.put(p.getName(), e.getClickedBlock().getLocation());
					if(tempPos1.containsKey(p.getName())) {
						p.sendMessage("§aBeide Positionen gesetzt, erstelle nun die Arena mit /LavaRun Create.");
					} else
						p.sendMessage(instance.getData().Prefix() + "§aPosition 2 §bgesetzt.");
				}
			}
		}
	}
}
