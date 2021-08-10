package de.mcnext.lavarun.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.mcnext.lavarun.LavaRun;

public class BuildListener implements Listener {
	
	private LavaRun instance;
	
	public BuildListener(LavaRun instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!instance.getData().getInBuildMode().contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(!instance.getData().getInBuildMode().contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
}
