package de.mcnext.lavarun.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.mcnext.lavarun.LavaRun;

public class MoveListener implements Listener {
	
	private LavaRun instance;
	
	public MoveListener(LavaRun instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(instance.getGameManager().isRunning()) {
			
			if(p.getLocation().getBlockY() <= instance.getGameManager().getLowY()-1) {
				if(!instance.getGameManager().isLost()) {
					p.sendMessage("Game Over");
					p.setGameMode(GameMode.CREATIVE);
					instance.getGameManager().setLost(true);
					instance.getGameManager().stopCheckLava();
					instance.getGameManager().stopTimer();
				}
			}
			
		} else if(!instance.getData().getInBuildMode().contains(p.getName()))
			e.setCancelled(true);
			
	}

}
