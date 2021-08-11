package de.mcnext.lavarun.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.mcnext.lavarun.LavaRun;

public class ConnectionListener implements Listener {
	
	private LavaRun instance;
	
	public ConnectionListener(LavaRun instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		
		instance.setTarget(p);
		
		p.teleport(instance.getLocManager().getLocation("Spawn"));
		
		if(!instance.getCountdown().isRunning()) {
			instance.getCountdown().start();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		
		if(instance.getCountdown().isRunning()) {
			instance.getCountdown().stop();
		}
	}

}
